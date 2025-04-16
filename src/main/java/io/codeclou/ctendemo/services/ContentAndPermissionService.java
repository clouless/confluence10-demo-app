package io.codeclou.ctendemo.services;

import com.atlassian.confluence.content.service.BlogPostService;
import com.atlassian.confluence.content.service.DraftService;
import com.atlassian.confluence.content.service.PageService;
import com.atlassian.confluence.content.service.blogpost.BlogPostLocator;
import com.atlassian.confluence.core.service.NotValidException;
import com.atlassian.confluence.pages.BlogPost;
import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.pages.CommentManager;
import com.atlassian.confluence.pages.Draft;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.security.Permission;
import com.atlassian.confluence.security.PermissionManager;
import com.atlassian.confluence.user.ConfluenceUser;

// SPRING-BEAN
public class ContentAndPermissionService {

    private final DraftService draftService;
    private final PermissionManager permissionManager;
    private final CommentManager commentManager;
    private final BlogPostService blogPostService;
    private final PageService pageService;

    public ContentAndPermissionService(DraftService draftService,
                                       BlogPostService blogPostService,
                                       CommentManager commentManager,
                                       PermissionManager permissionManager,
                                       PageService pageService) {
        this.draftService = draftService;
        this.commentManager = commentManager;
        this.blogPostService = blogPostService;
        this.permissionManager = permissionManager;
        this.pageService = pageService;
    }

    public boolean checkPermission(Long contentId, ConfluenceUser confluenceUser) {
        //
        // STEP 1: find blogPost, page or comment
        //
        Page page = getPageById(contentId);
        if (page != null) {
            boolean hasPermission = permissionManager.hasPermission(confluenceUser, Permission.VIEW, page);
            return hasPermission;
        }

        Comment comment = getCommentById(contentId);
        if (comment != null) {
            boolean hasPermission = permissionManager.hasPermission(confluenceUser, Permission.VIEW, comment);
            return hasPermission;
        }

        BlogPost blogPost = getBlogPost(contentId);
        if (blogPost != null) {
            boolean hasPermission = permissionManager.hasPermission(confluenceUser, Permission.VIEW, blogPost);
            return hasPermission;
        }

        //
        // STEP 2: Check if content is draft (needed in macro preview when user adds macro to draft blogpost or comment)
        //
        Draft draft = findDraftByIdOrNull(contentId);
        if (draft != null) {
            boolean hasPermission = permissionManager.hasPermission(confluenceUser, Permission.VIEW, draft);
            return hasPermission;
        }

        //
        // STEP 3: ELSE FALSE
        //
        return false;
    }

    public Comment getCommentById(Long pageId) {
        return commentManager.getComment(pageId);
    }

    public BlogPost getBlogPost(Long pageId) {
        BlogPostLocator bl = blogPostService.getIdBlogPostLocator(pageId);
        if (bl != null) {
            return bl.getBlogPost();
        } else {
            return null;
        }
    }

    public Draft findDraftByIdOrNull(Long contentId) {
        try {
            return draftService.getDraft(contentId);
        } catch (NotValidException notValidException) {
            return null;
        }
    }

    public Page getPageById(Long pageId) {
        try {
            return this.pageService.getIdPageLocator(pageId).getPage();
        } catch (Exception e) {
            return null;
        }
    }
}

package controllers;


import models.BlogPost;
import models.User;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by chad on 3/9/16.
 */

/*
 * This controller contains Posting and Commenting logic. All methods require user to be
 * authenticated.
 */
@Security.Authenticated(Secured.class)
public class Post extends Controller {

    public Result addPost() {
        Form<PostForm> postForm = Form.form(PostForm.class).bindFromRequest();

        if (postForm.hasErrors()) {
            return badRequest(postForm.errorsAsJson());
        } else {
            BlogPost newBlogPost = new BlogPost();
            newBlogPost.commentCount = 0L;
            newBlogPost.subject = postForm.get().subject;
            newBlogPost.content = postForm.get().content;
            newBlogPost.user = getUser();
            newBlogPost.save();
        }
        return ok(HomeController.buildJsonResponse("success", "Post added successfully"));
    }


    private User getUser() {
        return User.findByEmail(session().get("username"));
    }

    public class PostForm {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String subject;

        @Constraints.Required
        public String content;

    }
}

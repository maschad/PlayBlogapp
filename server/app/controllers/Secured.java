package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by chad on 3/9/16.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context context){
        return context.session().get("Username");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return unauthorized();
    }
}

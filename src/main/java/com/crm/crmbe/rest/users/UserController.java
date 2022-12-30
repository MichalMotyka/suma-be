package com.crm.crmbe.rest.users;

import com.crm.crmbe.database.services.PermissionSerices;
import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.Role;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.entity.UserComponent;
import com.crm.crmbe.entity.UserData;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    PermissionSerices permissionSerices;

    ObjectResponse objectResponse = new ObjectResponse();

    @GetMapping("/api/v1/users/getAll")
    public String getAll(){
        List<UserData> userData = new ArrayList<>();
        userServices.findAllUser().forEach(value->{
            userData.add(new UserData(value.getId(),value.getLogin(),value.getRole()));
        });
        return objectResponse.UsersListJsonList(userData);
    }

    @GetMapping("/api/v1/users/getroles")
    public String getAllRoles(){
        return objectResponse.RoleListJsonList(Role.roleList);
    }

    @PutMapping("/api/v1/users/register")
    public void register(@RequestBody UserComponent user, HttpServletResponse response) throws IOException {
        if (userServices.createUser(user)){
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_CONFLICT);
    }
    @GetMapping("/api/v1/users/get_active_role")
    public String getUserPermisions(@RequestParam String id){
      return objectResponse.RoleListJsonList(permissionSerices.mapPermisionsToRole(permissionSerices.findPermisionByUserId(id)));
    }
}

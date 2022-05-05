package gov.iti.jets.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.mappers.UserMapper;
import gov.iti.jets.presentation.dtos.UserDto;
import gov.iti.jets.services.UserService;
import gov.iti.jets.services.impl.UserServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("users")
public class UserController {

    @Autowired
    public static UserService userService = new UserServiceImpl();
    @Autowired
    public static UserMapper userMapper = UserMapper.INSTANCE;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllUsers() {
        System.out.println("here");
        List<UserDto> users = new ArrayList<>();

        userService.getUsers().forEach(u -> users.add(userMapper.userToDto(u)));
        if (users.isEmpty()) {
            return Response.ok().entity("There is no products").build();
        }
        return Response.ok().entity(users).build();
    }

    @GET
    @Path("{pid}")
    @Produces({ "application/xml", "application/json" })
    public Response getUserByID(@PathParam("pid") int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return Response.ok().entity("You need to register first").build();
        }
        UserDto userDto = UserMapper.INSTANCE.userToDto(user);
        return Response.ok().entity(userDto).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToEntity(userDto);
        UserDto user2 =UserMapper.INSTANCE.userToDto(userService.login(user.getEmail(), user.getPassword()));
        if (user2 != null) {
            return Response.ok().entity("there is user hava this email and password please login").build();
        }
        userService.addUser(user);
        return Response.ok().entity("Successfully Added").build();
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateUser(UserDto userDto) {
        UserDto user2 =UserMapper.INSTANCE.userToDto(userService.login(userDto.getEmail(), userDto.getPassword()));
        
        if (user2 != null) {
            userService.updateUser(userDto, user2.getId());
            return Response.ok().entity("Successfully Updated").build();
        }
        return Response.ok().entity("There is NO user hava this email and password").build();

    }

    @DELETE
    @Path("{oid}")
    public Response deleteOrder(@PathParam("oid") int id) {
        UserDto user = UserMapper.INSTANCE.userToDto(userService.getUser(id));
        if (user == null) {
            return Response.ok().entity("There is no user to delete it ").build();
        }
        userService.removeUser(id);
        return Response.ok().entity("Successfully Deteted").build();
    }

}

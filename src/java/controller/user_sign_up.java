package controller;

import beans.userbean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "user_sign_up", urlPatterns = {"/user_sign_up"})
public class user_sign_up extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int count = 0;
            userbean usb = new userbean();

            usb.setUsername(request.getParameter("uname"));
            usb.setPassword(request.getParameter("pass"));

            String username = usb.getUsername();
            String password = usb.getPassword();

            ResultSet search = DB.search("SELECT COUNT(*) FROM `user`");
            if (search.next()) {
                count += Integer.parseInt(search.getString(1));
            }
            String id = "" + count;

            DB.iud("INSERT INTO `user`(`id`, `un`, `ps`) VALUES ('"+id+"','"+username+"','"+password+"')");
            response.sendRedirect("user_signup.jsp?message=successfully signed up");
        } catch (Exception ex) {
            PrintWriter writer = response.getWriter();
            writer.print(ex);
        }

    }

}

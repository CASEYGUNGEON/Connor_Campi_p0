package dev.gungeon.data;

import dev.gungeon.entities.Account;
import dev.gungeon.entities.UserAcc;
import dev.gungeon.utilities.ConnectionUtil;
import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.structures.LinkedList;

import java.sql.*;

public class UserAccDAOImpl implements UserAccDAO {

    @Override
    public UserAcc CreateUser(UserAcc user) throws ElementExistsException {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "select * from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.GetName());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                throw new ElementExistsException("User already exists");
            }

            sql = "insert into users values (default,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.GetName());
            ps.setString(2,user.GetPassword());

            ps.execute();

            rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("user_id");
            user.SetId(generatedId);
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserAcc GetUser(int id) throws ElementNotFoundException {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "select * from users where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                throw new ElementNotFoundException("No such user");
            }
            UserAcc user = new UserAcc(rs.getInt("acc_id"));
            user.SetName(rs.getString("name"));
            user.SetName(rs.getString("username"));
            user.SetPassword(rs.getString("password"));
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserAcc GetUser(String name) throws ElementNotFoundException {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "select * from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);

            ResultSet rs = ps.executeQuery();
            rs.next();
            UserAcc user = new UserAcc(rs.getInt("user_id"));
            user.SetName(rs.getString("username"));
            user.SetPassword(rs.getString("password"));
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserAcc UpdateUser(UserAcc user) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "update users set username = ?, password = ? where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.GetName());
            ps.setString(2,user.GetPassword());
            ps.setInt(3,user.GetId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean DeleteUser(int id) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "delete from users where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteUser(UserAcc user) {
        return DeleteUser(user.GetId());
    }

    @Override
    public LinkedList<Account> GetAccounts(int id) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "select * from accounts where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            LinkedList<Account> acclist = new LinkedList<Account>();
            while(rs.next()) {
                acclist.Add(new Account(rs.getInt("acc_id")));
                Account acc = acclist.GetLast().Get();
                acc.SetName(rs.getString("acc_name"));
                acc.SetBalance(rs.getDouble("balance"));
                acc.SetOwner(rs.getInt("user_id"));
            }
            return acclist;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
package dev.gungeon.data;

import dev.gungeon.entities.Account;
import dev.gungeon.utilities.ConnectionUtil;
import dev.gungeon.utilities.structures.LinkedList;
import dev.gungeon.utilities.structures.Node;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.Logger;

import java.sql.*;

public class AccountDAOImpl {

    public Account CreateAccount(Account acc) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "insert into accounts values (default,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,acc.GetName());
            ps.setInt(2,acc.GetOwner());
            ps.setDouble(3,acc.GetBalance());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("acc_id");
            acc.SetIdentifier(generatedId);
            return acc;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Account GetAccount(int id) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "select * from accounts where acc_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Account acc = new Account(rs.getInt("acc_id"));
            acc.SetName(rs.getString("name"));
            acc.SetOwner(rs.getInt("owner"));
            acc.SetBalance(rs.getDouble("balance"));
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Account UpdateAccount(Account acc) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "update accounts set name = ?, balance = ? where acc_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,acc.GetName());
            ps.setDouble(2,acc.GetBalance());
            ps.setInt(3,acc.GetIdentifier());

            ps.executeUpdate();
            return acc;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean DeleteAccount(int id) {
        try {
            Connection conn = ConnectionUtil.CreateConnection();
            String sql = "delete from accounts where acc_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private LinkedList<Double> GetHistory(int accid, Connection conn) {

    }

    private LinkedList<Double> UpdateHistory(Account acc, Connection conn) {
        try {
            String sql = "select COUNT(*) from history where acc_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, acc.GetIdentifier());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            LinkedList<Double> hist = acc.GetHistory();
            if(count < hist.Size())
            {
                hist.GoToIndex(count);
                Node<Double> cur = hist.GetCurrentNode();
                while(cur != null)
                {
                    String sql = "insert into history values (default, ?, ?");
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1,acc.GetIdentifier());
                    ps.setDouble(2,cur.Get());
                    ps.execute();
                }
            }
            return hist;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

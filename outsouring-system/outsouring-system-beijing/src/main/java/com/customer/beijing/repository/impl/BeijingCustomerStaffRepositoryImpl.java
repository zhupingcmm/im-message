package com.customer.beijing.repository.impl;

import cn.hutool.core.util.ObjectUtil;
import com.customer.beijing.entity.BeijingCustomerStaff;
import com.customer.beijing.repository.BeijingCustomerStaffRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class BeijingCustomerStaffRepositoryImpl implements BeijingCustomerStaffRepository {

    private final DataSource dataSource;
    @Override
    public List<BeijingCustomerStaff> findCustomerStaff() {
        List<BeijingCustomerStaff> staffs = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from `beijing_customer_staff`");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffs.add(convertStaff(resultSet));
            }
        } catch (SQLException e){
            log.error("Error ", e);
        } finally {
            close(connection, statement, resultSet);
        }


        return staffs;
    }

    private void close(Connection connection,  PreparedStatement statement, ResultSet resultSet) {
        if (ObjectUtil.isNotEmpty(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (ObjectUtil.isNotEmpty(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (ObjectUtil.isNotEmpty(statement)) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private BeijingCustomerStaff convertStaff(ResultSet resultSet) throws SQLException {
        BeijingCustomerStaff staff = new BeijingCustomerStaff();
        staff.setId(resultSet.getLong("id"));
        staff.setNickname(resultSet.getString("nickname"));
        staff.setGender(resultSet.getString("gender"));
        staff.setPhone(resultSet.getString("phone"));
        staff.setAvatar(resultSet.getString("avatar"));
        staff.setGoodAt(resultSet.getString("good_at"));
        staff.setRemark(resultSet.getString("remark"));

        return staff;
    }

    @Override
    public List<BeijingCustomerStaff> findCustomerStaffByUpdatedTime(Long updatedTime) {
        List<BeijingCustomerStaff> staffs = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("select * from `beijing_customer_staff` where updated_at > ? ");
            statement.setTimestamp(1, new Timestamp(updatedTime));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                staffs.add(convertStaff(resultSet));
            }
        } catch (SQLException e){
            log.error("Error ", e);
        } finally {
            close(connection, statement, resultSet);
        }

        return staffs;
    }

    @Override
    public Long createCustomerStaff(BeijingCustomerStaff customerStaff) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("insert into `beijing_customer_staff` (nickname, avatar, phone, gender, good_at, remark) values (?, ?, ?, ?, ?, ?)");
            statement.setString(1, customerStaff.getNickname());
            statement.setString(2, customerStaff.getAvatar());
            statement.setString(3, customerStaff.getPhone());
            statement.setString(4, customerStaff.getGender());
            statement.setString(5, customerStaff.getGoodAt());
            statement.setString(6, customerStaff.getRemark());

            return (long) statement.executeUpdate();
        } catch (SQLException e){
            log.error("Error ", e);
        } finally {
            close(connection, statement, null);
        }

        return 0L;
    }

    @Override
    public Boolean updateCustomerStaff(BeijingCustomerStaff customerStaff) {

        Connection connection = null;
        PreparedStatement statement = null;

        int result = 0;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("update `beijing_customer_staff` set  nickname = ?, avatar = ?, phone = ?, gender = ?, good_at =?, remark = ? where id = ?");
            statement.setString(1, customerStaff.getNickname());
            statement.setString(2, customerStaff.getAvatar());
            statement.setString(3, customerStaff.getPhone());
            statement.setString(4, customerStaff.getGender());
            statement.setString(5, customerStaff.getGoodAt());
            statement.setString(6, customerStaff.getRemark());
            statement.setLong(7, customerStaff.getId());
            result = statement.executeUpdate();
        } catch (SQLException e){
            log.error("Error ", e);
        } finally {
            close(connection, statement, null);
        }

        return result != 0;
    }

    @Override
    public Boolean deleteCustomerStaffById(Long id) {

        Connection connection = null;
        PreparedStatement statement = null;

        int result = 0;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("update `beijing_customer_staff` set  is_deleted = ? where id = ?");
            statement.setInt(1, 1);
            statement.setLong(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e){
            log.error("Error ", e);
        } finally {
            close(connection, statement, null);
        }

        return result != 0;
    }
}

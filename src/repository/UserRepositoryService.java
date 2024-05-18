package repository;

import dao.UserDao;
import jdk.jfr.Category;
import model.other.Hotel;
import model.other.Room;
import model.user.Client;
import model.user.Manager;
import model.user.Receptionist;
import model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRepositoryService {
    private UserDao userDao;

    private static UserRepositoryService instance;

    private UserRepositoryService(Statement statement){
        userDao = new UserDao();
        createDefaultUsers(statement);
    }

    private void createDefaultUsers(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotelrezervation.users join hotelrezervation.userstype where usertypeId = idUsersType;");
            while(resultSet.next()){
                String type =resultSet.getString("Name");
               // System.out.println(type);
                if (type.equals("Admin")){
                    Manager manager = new Manager(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password"),
                            resultSet.getString("emailAddress"),
                            resultSet.getInt("idUsers"));
                    int salary = Integer.parseInt(resultSet.getString("salary"));
                    manager.setSalary(salary);
                    userDao.create(manager);
                }else if(type.equals("User")){
                    Client client = new Client(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password"),
                            resultSet.getString("emailAddress"),
                            resultSet.getInt("idUsers"));
                    userDao.create(client);
                }else if(type.equals("Receptionist")){
                    Receptionist receptionist = new Receptionist(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password"),
                            resultSet.getString("emailAddress"),
                            resultSet.getInt("idUsers"));
                    int salary = Integer.parseInt(resultSet.getString("salary"));
                    receptionist.setSalary(salary);
                    int hotelId = Integer.parseInt(resultSet.getString("hotelId"));
                    receptionist.setHotelId(hotelId);

                    double review = Double.parseDouble(resultSet.getString("review"));
                    receptionist.setReview(review);
                    userDao.create(receptionist);
                }
              }

        }catch (SQLException e){
           e.printStackTrace();
        }

       }

    public static UserRepositoryService getInstance(Statement statement) {
        if(instance == null){
         instance = new UserRepositoryService(statement);
        }
        return instance;
    }
    public void addUser(User newUser, Connection connection){
        if(userDao.read(newUser.getEmailAddress()) != null){
            System.out.println("Email address must be unique, please login!");
        }else{
            String insertQuery = "INSERT INTO hotelrezervation.users (emailAddress, firstName, lastName,password,usertypeId)" +
                    " VALUES (?, ?, ?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, newUser.getEmailAddress());
                preparedStatement.setString(2, newUser.getFirstName());
                preparedStatement.setString(3, newUser.getLastName());
                preparedStatement.setString(4, newUser.getPassword());
                preparedStatement.setInt(5, 3);
                int rowsAffected = preparedStatement.executeUpdate();
                insertQuery = "SELECT idUsers FROM hotelrezervation.users WHERE emailAddress = ?";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, newUser.getEmailAddress());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idUser = resultSet.getInt("idUsers");
                    newUser.setId(idUser);
                }
                preparedStatement.close();
                connection.close();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            userDao.create(newUser);
        }
    }

    public List<User> getFilteredUsers(String type){
        List<User> userList = new ArrayList<>(userDao.getUsers());
        List<User> filteredUsers  =
                userList.stream()
                        .filter(user->user.getType().toLowerCase().equals(type))
                        .toList();
        return filteredUsers;

    }
    public User find(User searchUser){

        User promising = userDao.read(searchUser.getEmailAddress());
        if(promising != null){
            //aici fac un find sa gasesc userul cu acelasi email, dar poate au parolele diferite
            if(promising.equals(searchUser)){
                // de siguranta verific inca o data prin alta metoda, iar aici verific practic si
                // parola daca e aceeasi
                return promising;
            }
        }

        return null;
    }
    public User find(int id){

        User promising = userDao.read(id);
        if(promising != null){
                return promising;
            }
        return null;
    }
}

package com.example.taco2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class OrderRepository implements IOrder{
  private SimpleJdbcInsert orderInsert;
  private SimpleJdbcInsert tacoOrderInsert;
  private JdbcTemplate jdbcTemplate;
  private ObjectMapper objectMapper ;
  public OrderRepository(JdbcTemplate jdbcTemplate){
      this.orderInsert = new SimpleJdbcInsert(jdbcTemplate)
              .withTableName("taco_order")
              .usingGeneratedKeyColumns("id");
      this.tacoOrderInsert = new SimpleJdbcInsert(jdbcTemplate)
              .withTableName("taco_Orders");
      this.objectMapper = new ObjectMapper();
      this.jdbcTemplate = jdbcTemplate;
  }
  private Order OrderObjectMapper(ResultSet rs,int numRow) throws SQLException {
      return new Order(

              rs.getString("deliveryName"),
              rs.getString("deliveryStreet"),
              rs.getString("deliveryCity"),
              rs.getString("deliveryState"),
              rs.getString("deliveryZip"),
              rs.getString("ccNumber"),
              rs.getString("ccExpiration"),
              rs.getString("ccCVV"),
              new Date(rs.getLong("placedAt"))
      );
  }
    private Long saveOrderAndReturnGeneretedKey(Order order){

        Map<String, Object> values = objectMapper.convertValue(order,Map.class);
        values.put("placedAt",order.getPlacedAt());
        Long orderId = orderInsert.executeAndReturnKey(values).longValue();
        return orderId;
    }
    private void saveTacoAndOrder(long orderId,Taco taco){
      Map<String,Object> values = new HashMap<>();
      values.put("order",orderId);
      values.put("order",taco.getTacoId());
      tacoOrderInsert.execute(values);
  }

    @Override
    public Order findOneOrder(long orderId) {
      String sql = "select deliveryName,deliveryStreet,deliveryCity,deliveryState,delivery,ccNumber,ccExpiration,ccCVV,placedAt from Order where id = ?";
      Order order = jdbcTemplate.queryForObject(sql, this::OrderObjectMapper ,orderId);
      return order;
    }

    @Override
    public List<Order> findAll() {
        String sql = "select deliveryName,deliveryStreet,deliveryCity,deliveryState,delivery,ccNumber,ccExpiration,ccCVV,placedAt from Order ";
        return jdbcTemplate.query(sql,this::OrderObjectMapper);
    }

    @Override
    public Order saveOrder(Order order) {
        Long orderId = saveOrderAndReturnGeneretedKey(order);
        for (Taco taco : order.getTaco()){
            saveTacoAndOrder(orderId,taco);
        }
        return order;
    }
}

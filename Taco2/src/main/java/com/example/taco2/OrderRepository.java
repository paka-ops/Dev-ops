package com.example.taco2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository implements IOrder{
    private SimpleJdbcInsert orderInsert;
    private SimpleJdbcInsert tacoOrderInsert;
    private ObjectMapper objectMapper;
    public OrderRepository(JdbcTemplate jdbcTemplate){
        this.orderInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("taco_Order")
                .usingGeneratedKeyColumns("id");
        this.tacoOrderInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("taco_Order_tacos");
        this.objectMapper = new ObjectMapper();
    }
    private Long saveOrderAndReturnId(Order order){

        Map<String, Object> values = objectMapper.convertValue(order,Map.class);
        order.setPlacedAt(new Timestamp(new Date().getTime()));
        values.put("placedAt",order.getPlacedAt());
        Long orderId = orderInsert.executeAndReturnKey(values).longValue();
        return orderId;
    }
    private void saveTacoAndOrder(Taco taco,long orderId){
        Map<String,Object> values = new HashMap<String,Object>();
        values.put("tacoOrder",orderId);
        values.put("taco",taco.getTacoId());
        tacoOrderInsert.execute(values);
    }
    private Order orderRowMapper(ResultSet rs,int numRow) throws SQLException {
        return new Order(
                rs.getString("name"),
                rs.getString("street"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("street"),
                rs.getString("zip"),
                rs.getString("ccnumber"),
                rs.getString("ccexpiration"),
                rs.getString("cccvv")

        );
    }
    @Override
    public Order findOneOrder(long ordId) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order saveOrder(Order order) {
        Long orderId = saveOrderAndReturnId(order);

        for(Taco taco : order.getTaco()){
            saveTacoAndOrder(taco,orderId);
        }
        return order;
    }
}

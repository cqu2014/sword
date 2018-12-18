package oliver.shein.sword.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Oliver Wang
 * @Description 动态创建队列并监听
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/12/17 14:56
 */
@Configuration
@Slf4j
public class RabbitUtil {
    /**
     * 定义常量 RabbitAdmin
     */
    private final RabbitAdmin rabbitAdmin;
    /**
     * 定义常量rabbitTemplate
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * 注入自定义的配置
     * 构造函数里初始化常量
     *
     * @param rabbitAdmin
     * @param rabbitTemplate
     */
    @Autowired
    public RabbitUtil(RabbitAdmin rabbitAdmin, RabbitTemplate rabbitTemplate){
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 转换Message对象
     * @param messageType 返回消息类型 MessageProperties类中常量
     * @param msg
     * @return
     */
    public Message getMessage(String messageType, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(messageType);
        Message message = new Message(msg.toString().getBytes(),messageProperties);
        return message;
    }

    /**
     * 有绑定Key的Exchange发送
     * @param routingKey
     * @param msg
     */
    public void sendMessageToExchange(TopicExchange topicExchange, String routingKey, Object msg){
        Message message = getMessage(MessageProperties.CONTENT_TYPE_JSON,msg);
        rabbitTemplate.send(topicExchange.getName(), routingKey, message);
    }

    /**
     * 没有绑定KEY的Exchange发送
     * @param exchange
     * @param msg
     */
    public void sendMessageToExchange(TopicExchange topicExchange, AbstractExchange exchange, String msg){
        addExchange(exchange);
        log.info("RabbitMQ send "+exchange.getName()+"->"+msg);
        rabbitTemplate.convertAndSend(topicExchange.getName(),msg);
    }

    /**
     * 给queue发送消息
     * @param queueName
     * @param msg
     */
    public void sendToQueue(String queueName, String msg){
        sendToQueue(DirectExchange.DEFAULT, queueName, msg);
    }

    /**
     * 给direct交换机指定queue发送消息
     * @param directExchange
     * @param queueName
     * @param msg
     */
    public void sendToQueue(DirectExchange directExchange, String queueName, String msg){
        Queue queue = new Queue(queueName);
        addQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).withQueueName();
        rabbitAdmin.declareBinding(binding);
        //设置消息内容的类型，默认是 application/octet-stream 会是 ASCII 码值
        rabbitTemplate.convertAndSend(directExchange.getName(), queueName, msg);
    }

    /**
     * 给queue发送消息
     *
     * @param queueName
     * @return
     */
    public String receiveFromQueue(String queueName){
        return receiveFromQueue(DirectExchange.DEFAULT, queueName);
    }

    /**
     * 给direct交换机指定queue发送消息
     * @param directExchange
     * @param queueName
     * @param
     */
    public String receiveFromQueue(DirectExchange directExchange, String queueName){
        Queue queue = new Queue(queueName);
        addQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).withQueueName();
        rabbitAdmin.declareBinding(binding);
        String messages = (String)rabbitTemplate.receiveAndConvert(queueName);
        System.out.println("Receive:"+messages);
        return messages;
    }

    /**
     * 创建Exchange
     * @param exchange
     */
    public void addExchange(AbstractExchange exchange){
        rabbitAdmin.declareExchange(exchange);
    }

    /**
     * 删除一个Exchange
     * @param exchangeName
     */
    public boolean deleteExchange(String exchangeName){
        return rabbitAdmin.deleteExchange(exchangeName);
    }


    /**
     * Declare a queue whose name is automatically named. It is created with exclusive = true, autoDelete=true, and
     * durable = false.
     * @return Queue
     */
    public Queue addQueue(){
        return rabbitAdmin.declareQueue();
    }

    /**
     * 创建一个指定的Queue
     * @param queue
     * @return queueName
     */
    public String addQueue(Queue queue){
        return rabbitAdmin.declareQueue(queue);
    }

    /**
     * Delete a queue.
     * @param queueName the name of the queue.
     * @param unused true if the queue should be deleted only if not in use.
     * @param empty true if the queue should be deleted only if empty.
     */
    public void deleteQueue(String queueName, boolean unused, boolean empty){
        rabbitAdmin.deleteQueue(queueName,unused,empty);
    }

    /**
     * 删除一个queue
     * @return true if the queue existed and was deleted.
     * @param queueName
     */
    public boolean deleteQueue(String queueName){
        return rabbitAdmin.deleteQueue(queueName);
    }

    /**
     * 绑定一个队列到一个匹配型交换器使用一个routingKey
     * @param queue
     * @param exchange
     * @param routingKey
     */
    public void addBinding(Queue queue ,TopicExchange exchange,String routingKey){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 绑定一个Exchange到一个匹配型Exchange 使用一个routingKey
     * @param exchange
     * @param topicExchange
     * @param routingKey
     */
    public void addBinding(Exchange exchange,TopicExchange topicExchange,String routingKey){
        Binding binding = BindingBuilder.bind(exchange).to(topicExchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 去掉一个binding
     * @param binding
     */
    public void removeBinding(Binding binding){
        rabbitAdmin.removeBinding(binding);
    }
}

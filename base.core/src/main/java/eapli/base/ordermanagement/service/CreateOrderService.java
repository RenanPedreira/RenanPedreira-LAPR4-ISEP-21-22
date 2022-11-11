package eapli.base.ordermanagement.service;

import eapli.base.common.domain.model.Address;
import eapli.base.common.domain.model.Identifier;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.ordermanagement.dto.ClerkOrderDTO;
import eapli.base.ordermanagement.dto.ClerkOrderMapper;
import eapli.base.ordermanagement.dto.OrderItemDTO;
import eapli.base.ordermanagement.dto.OrderItemMapper;
import eapli.base.ordermanagement.persistence.OrderRepository;
import eapli.base.productmanagement.domain.Producto.Price;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.domain.Producto.ShortDescription;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.persistence.ProductRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateOrderService {

    private final CustomerRepository clientRepo = PersistenceContext.repositories().customerRepository();
    private final OrderRepository orderRepo = PersistenceContext.repositories().orders();
    private final ProductRepository productRepo = PersistenceContext.repositories().products();

    SystemUser clerk = AuthzRegistry.authorizationService().session().get().authenticatedUser();

    private ClerkOrder order;
    private List<OrderItem> orderItemList = new ArrayList<>();

    public ClerkOrderDTO createOrderForClient(String sourceChannel, String comment, String paymentMethod, String
            clientEmail, String[] billingAddress, String[] deliveringAddress){

        Long id = orderRepo.count() + 1;
        Customer customer = clientRepo.findByEmail(clientEmail).get(0);

        ClerkOrderBuilder builder = new ClerkOrderBuilder();
        builder.numericIdentifier(new OrderNumericIdentifier(Math.toIntExact(id)));
        builder.clerk(clerk);
        builder.sourceChannel(new SourceChannel(sourceChannel));
        builder.comment(new Comment(comment));
        builder.customer(customer);
        builder.billingAddress(new Address("BILLING_ADDRESS", billingAddress[0], billingAddress[1], Integer.parseInt(billingAddress[2])));
        builder.deliveringAddress(new Address("DELIVERY_ADDRESS", deliveringAddress[0], deliveringAddress[1], Integer.parseInt(deliveringAddress[2])));
        builder.paymentMethod(new PaymentMethod(paymentMethod));

        order = builder.build();

        ClerkOrderMapper map = new ClerkOrderMapper();
        return map.toDTO(order);
    }

    public void createOrderItem(ProductDTO productDTO, int quantity){
        List<Product> products = productRepo.getProductByID(productDTO.dtoId());
        Product product = products.get(0);
        Quantity q = new Quantity(quantity);
        Price p = new Price(Double.parseDouble(product.getPrice()));
        ShortDescription d = new ShortDescription(product.getShortDescription());

        OrderItemBuilder builder = new OrderItemBuilder();
        builder.product(product);
        builder.quantity(q);

        OrderItem item = builder.build();
        orderItemList.add(item);
        order.addOrderItem(item);
    }

    public List<OrderItemDTO> getAllOrderItensAdded(){
        OrderItemMapper map = new OrderItemMapper();
        return map.toDTO(orderItemList);
    }

    public void confrimOrder(){
        System.out.println(orderRepo.toString());
        orderRepo.save(order);
    }

}

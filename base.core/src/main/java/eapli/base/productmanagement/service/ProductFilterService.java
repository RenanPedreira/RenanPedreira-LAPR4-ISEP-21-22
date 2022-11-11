package eapli.base.productmanagement.service;
import eapli.base.infrastructure.persistence.PersistenceContext;

import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.dto.ProductMapper;
import eapli.base.productmanagement.persistence.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Renan Pedreira
 */
class AlphaComparator implements Comparator
{
    @Override
    public int compare(Object o1, Object o2)
    {
        Product p1=(Product)o1;
        Product p2=(Product)o2;

        return p1.getName().compareTo(p2.getName());
    }
}

class PriceHighComparator implements Comparator
{
    @Override
    public int compare(Object o1, Object o2)
    {
        Product p1=(Product)o1;
        Product p2=(Product)o2;

        return p1.getPrice().compareTo(p2.getPrice());
    }
}

class PriceLowComparator implements Comparator
{
    @Override
    public int compare(Object o1, Object o2)
    {
        Product p1=(Product)o1;
        Product p2=(Product)o2;

        return -(p1.getPrice().compareTo(p2.getPrice()));
    }
}

public class ProductFilterService {
    //private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final ProductRepository repo = PersistenceContext.repositories().products();

    List<Product> productList1 = repo.getAllProducts();
    List<Product> productList2 = repo.getAllProducts();

    public void filterByCategory(String category){
        productList2 = repo.getProductsFromCategory(category);
        commonProducts();
    }

    public void filterByDescription(String description){
        productList2 = repo.getProductsFromDescription(description);
        commonProducts();
    }

    public void filterByBrand(String brand){
        productList2 = repo.getProductsFromBrand(brand);
        commonProducts();
    }

    public void resetFilters(){
        productList1.clear();
        productList2.clear();

        productList1.addAll(repo.getAllProducts());
        productList2.addAll(repo.getAllProducts());
    }

    public void commonProducts() {
        List<Product> products = new ArrayList<>(productList1);
        productList1.clear();
        for (Product p : products) {
            if (productList2.contains(p))
                productList1.add(p);
        }
    }

    public List<ProductDTO> filterResult(int sort){
        ProductMapper map = new ProductMapper();
        sortProductList(sort);
        return map.toDTO(productList1);
    }

    public void sortProductList(int sort){
        switch (sort){
            case 1:
                Collections.sort(productList1, new AlphaComparator());
                break;

            case 2:
                Collections.sort(productList1, new PriceLowComparator());
                break;

            case 3:
                Collections.sort(productList1, new PriceHighComparator());
                break;
        }
    }

}

package eapli.base.productmanagement.dto;

import eapli.base.productmanagement.domain.Producto.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renan Pedreira
 */
public class ProductMapper {

    public List<ProductDTO> toDTO(List<Product> productList){
        List<ProductDTO> listProductDTO = new ArrayList<>();

        for(Product p : productList) {
            ProductDTO productDTO = new ProductDTO(p);
            listProductDTO.add(productDTO);
        }

        return listProductDTO;
    }
}

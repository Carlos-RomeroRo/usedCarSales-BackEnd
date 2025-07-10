package com.example.usedCarSales.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usedCarSales.dto.ShoppingCartDTO;
import com.example.usedCarSales.mapper.ShoppingCartMapper;
import com.example.usedCarSales.repository.ShoppingCartRepository;
import com.example.usedCarSales.repository.UserRepository;
import com.example.usedCarSales.service.interfaces.ShopppingCartService;
import com.example.usedCarSales.entity.ShoppingCart;
import com.example.usedCarSales.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShopppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ShoppingCartDTO createShoppingCart(ShoppingCartDTO shopppingCartDTO) {
        if(shoppingCartRepository.existsById(shopppingCartDTO.shoppingCartId())) {
            log.error("Shopping cart with ID {} already exists", shopppingCartDTO.shoppingCartId());
            throw new IllegalArgumentException("Shopping cart with this ID already exists");
        }
        try {
            User user = userRepository.findById(shopppingCartDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            ShoppingCart newShoppingCart = shoppingCartMapper.shoppingCartDTOToShoppingCart(shopppingCartDTO);
            newShoppingCart.setUser(user); // Assign User manually
            ShoppingCart savedShoppingCart = shoppingCartRepository.save(newShoppingCart);
            ShoppingCartDTO savedShoppingCartDTO = shoppingCartMapper.shoppingCartToShoppingCartDTO(savedShoppingCart);
            return savedShoppingCartDTO;
            
        } catch (Exception e) {
            log.error("Error creating shopping cart: {}", e.getMessage());
            throw new RuntimeException("Error creating shopping cart");
        }
    }
    @Override
    public ShoppingCartDTO updateShoppingCart(Long id, ShoppingCartDTO shopppingCartDTO) {
       try {
            ShoppingCart existingShoppingCart = shoppingCartRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Shopping cart not found"));
            if(shopppingCartDTO.userId() != null) {
                User user = userRepository.findById(shopppingCartDTO.userId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                existingShoppingCart.setUser(user); // Assign User manually
            }
            ShoppingCart updatedShoppingCart = shoppingCartRepository.save(existingShoppingCart);
            log.info("Shopping cart with ID {} updated successfully", updatedShoppingCart.getIdShoppingCart());
            return shoppingCartMapper.shoppingCartToShoppingCartDTO(updatedShoppingCart);
       } catch (Exception e) {
            log.error("Error updating shopping cart: {}", e.getMessage());
            throw new RuntimeException("Error updating shopping cart");
       }
    }
    @Override
    @Transactional
    public boolean deleteShoppingCart(Long id) {
        try {
            if (!shoppingCartRepository.existsById(id)) {
                log.error("Shopping cart with ID {} does not exist", id);
                throw new IllegalArgumentException("Shopping cart with this ID does not exist");
            }
            shoppingCartRepository.deleteById(id);
            log.info("Shopping cart with ID {} deleted successfully", id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting shopping cart: {}", e.getMessage());
            throw new RuntimeException("Error deleting shopping cart");
        }
    }
    @Override
    public ShoppingCartDTO getShoppingCartById(Long id) {
        try{
            ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Shopping cart not found"));
            log.info("Shopping cart with ID {} retrieved successfully", id);
            return shoppingCartMapper.shoppingCartToShoppingCartDTO(shoppingCart);
        } catch (Exception e) {
            log.error("Error retrieving shopping cart: {}", e.getMessage());
            throw new RuntimeException("Error retrieving shopping cart: " + e.getMessage(), e);
        }
    }
    @Override
    public List<ShoppingCartDTO> getAllShoppingCarts() {
        try {
            List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
            log.info("Retrieved all shopping carts successfully");
            return shoppingCarts.stream().map(shoppingCartMapper::shoppingCartToShoppingCartDTO).toList();
        } catch (Exception e) {
            log.error("Error retrieving all shopping carts: {}", e.getMessage());
            throw new RuntimeException("Error retrieving all shopping carts: " + e.getMessage(), e);
        }
    }

}

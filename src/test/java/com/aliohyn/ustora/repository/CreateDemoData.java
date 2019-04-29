package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.ProfileChecker;
import com.aliohyn.ustora.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateDemoData {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileChecker profileChecker;

    @Autowired
    SlideItemRepository slideItemRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void addDemoData() {
         if(profileChecker.isDevProfile()) {
             //addAdmin();
             //addProducts();
             addSlideItems();
         }
    }

    private void addProducts() {
        Product product = productRepository.findByName("Canon camera");
        if(product == null) {
            product = new Product();
            product.setName("Canon camera");
            product.setDescription("Description");
            product.setPrice(200);
            product.setNewShow(true);
            product.setTopSellerShow(true);
            product.setBrand(getElseCreateBrand("Canon", "/img/brand1.png"));
            product.setSection(getElseCreateSection("Camera"));

            productRepository.save(product);

            product.setName("Nokia");
            product.setBrand(getElseCreateBrand("Nokia", "/img/brand2.png"));
            product.setSection(getElseCreateSection("Mobile"));
            productRepository.save(createProductCopy(product));

            product.setName("Apple new mac book 2015 March");
            product.setBrand(getElseCreateBrand("Apple", "/img/brand3.png"));
            product.setSection(getElseCreateSection("Laptop"));
            productRepository.save(createProductCopy(product));

            product.setName("Apple new mac book 2016");
            productRepository.save(createProductCopy(product));


            product.setName("Apple new mac book 2017");
            productRepository.save(createProductCopy(product));
        }
    }

    private Brand getElseCreateBrand(String name, String image) {
        Brand brand = brandRepository.findByName(name);
        if(brand == null) {
            brand = new Brand(name, image);
            brandRepository.save(brand);
        }
        return brand;
    }

    private Section getElseCreateSection(String name) {
        Section section = sectionRepository.findByName(name);
        if(section == null) {
            section = new Section(name);
            sectionRepository.save(section);
        }
        return section;
    }

    private Product createProductCopy(Product product) {
        Product productCopy = new Product();

        try {
            productCopy = product.clone();
            productCopy.setId(null);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }

        return productCopy;
    }

    private void addAdmin() {
        User user = userRepository.findByUsername("admin");
        if(user == null) {
            user = new User();

            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));

            userRepository.save(user);
        }
    }

    private void addSlideItems() {
        SlideItem slideItem = slideItemRepository.findByName("iPhone 6 Plus");
        if(slideItem == null) {
            slideItemRepository.save(new SlideItem("iPhone 6 Plus", "/ustora/img/h4-slide.png", "/", "Dual SIM"));
            slideItemRepository.save(new SlideItem("Apple Store Ipod", "/ustora/img/h4-slide2.png", "/", "Select Item"));
            slideItemRepository.save(new SlideItem("Apple Store Ipod", "/ustora/img/h4-slide3.png", "/", "Dual SIM"));
        }
    }
}

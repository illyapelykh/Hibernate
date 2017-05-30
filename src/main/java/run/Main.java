package run;

import entity.Product;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure()
                .addAnnotatedClass(User.class).addAnnotatedClass(Product.class)
                .buildSessionFactory();


        Session session = factory.openSession();

        session.beginTransaction();

        //session.save(new User("petro", "123"));

//        Product product1 = new Product("milk");
//        Product product2 = new Product("water");
//
//        ArrayList<Product> products = new ArrayList<Product>();
//        products.add(product1);
//        products.add(product2);
//
//        User user = new User("ivan", "123321");
//        user.setProducts(products);
//
//        session.save(user);

//        Product product = new Product("coconut", new User("fere", "111"));
//        session.save(product);

//        List<User> list = session.createQuery("select u from User u ", User.class).list();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> root = q.from(User.class);

        Predicate predicateByPassword = cb.equal(root.get("password"), "123321");
        Predicate predicateByName = cb.equal(root.get("name"), "ivan");

        Predicate predicate = cb.and(predicateByName, predicateByPassword);
        q.where(predicate);
        System.out.print(session.createQuery(q).list());

        session.getTransaction().commit();

        session.close();

        factory.close();

    }
}

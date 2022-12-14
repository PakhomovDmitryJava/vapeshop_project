import DAO.BaseDao;
import DAO.LiquidDao;
import DAO.LiquidLineDao;
import DAO.FlavourTypeDao;
import DAO.ManufacturerDao;
import DAO.NicConcDao;
import DAO.NicTypeDao;
import DAO.OrderDao;
import DAO.OriginCountryDao;
import DAO.UserDao;
import entity.Base;
import entity.Liquid;
import entity.LiquidLine;
import entity.FlavourType;
import entity.Manufacturer;
import entity.NicConc;
import entity.NicType;
import entity.Order;
import entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DaoRunner {
    public static void main(String[] args) {
//        saveUserTest();

//        deleteUserTest(4L);

//      findAllUsersTest();

//        updateUserTest();

//        saveOrderTest();

//        deleteOrderTest(9L);
//
//            updateOrderTest();
//

//        findAllOrdersTest();

//        saveOrderTest();


        findAllLiquidsTest();
//        findByIdLiquidTest(1L);
//            updateLiquidTest(2L);
//                saveLiquidTest();
//        deleteLiquidTest(1L);


//        findAllLiquidLinesTest();
//        findByIdLiquidLineTest(1L);
        //        updateLiquidLineTest();
////        saveLiquidLineTest();
//        deleteLiquidLineTest(1L);


//        findByIdBaseTest(1L);
//        findAllBaseTest();
//        updateBaseTest();
//        saveBaseTest();
//        deleteBaseTest();

//        findByIdBLiquidTasteTest(1L);
//        findAllLiquidTastesTest();
//        updateLiquidTasteTest();
//        saveLiquidTasteTest();
//        deleteLiquidTasteTest();

//        findByIdManufacturerTest(1L);
//        findAllManufacturerTest();
//        updateManufacturerTest();
//        saveManufacturerTest();
//        deleteManufacturerTest();

//        findByIdNicConcentrationTest(1L);
//        findAllNicConcentrationTest();
//        updateNicConcentrationTest();
//        saveNicConcentrationTest();
//        deleteNicConcentrationTest();

//        findByIdNicTypeTest(1L);
//        findAllNicTypeTest();
//        saveNicTypeTest();
//        updateNicTypeTest();
//        deleteNicTypeTest();
    }


    private static void findByIdLiquidLineTest(Long id) {
        var liquidLineDao = LiquidLineDao.getInstance();
        var mayBeLiquidLine = liquidLineDao.findById(1L);
        System.out.println(mayBeLiquidLine);
    }

    private static void findByIdLiquidTasteTest(Long id) {
        var liquidTasteDao = FlavourTypeDao.getInstance();
        var mayBeLiquidTaste = liquidTasteDao.findById(1L);
        System.out.println(mayBeLiquidTaste);
    }

    private static void findByIdBaseTest(Long id) {
        var baseDao = BaseDao.getInstance();
        var mayBeBase = baseDao.findById(1L);
        System.out.println(mayBeBase);
    }

    private static void findByIdManufacturerTest(Long id) {
        var manufacturerDao = ManufacturerDao.getInstance();
        var mayBeManufacturer = manufacturerDao.findById(1L);
        System.out.println(mayBeManufacturer);
    }

    private static void findByIdNicConcentrationTest(Long id) {
        var nicConcentrationDao = NicConcDao.getInstance();
        var mayNicConcentrationDao = nicConcentrationDao.findById(1L);
        System.out.println(mayNicConcentrationDao);
    }

    private static void findByIdNicTypeTest(Long id) {
        var nicTypeDao = NicTypeDao.getInstance();
        var mayBeNicType = nicTypeDao.findById(1L);
        System.out.println(mayBeNicType);
    }

    private static void findByIdLiquidTest(Long id) {
        var instance = LiquidDao.getInstance();
        var liquid = instance.findById(id);
        System.out.println(liquid);
    }

    private static void updateUserTest() {
        var userDao = UserDao.getInstance();
        var mayBeUser = userDao.findById(5L);
        System.out.println(mayBeUser);

        mayBeUser.ifPresent(user -> {
            user.setFirstName("Petr");
            user.setLastName("Petrov");
            userDao.update(user);
        });
    }

    private static void updateOrderTest() {
        var orderDao = OrderDao.getInstance();
        var mayBeOrder = orderDao.findById(2L);
        System.out.println(mayBeOrder);

        mayBeOrder.ifPresent(order -> {
            order.setPaid(true);
            order.setOrderDate(LocalDateTime.now().minusDays(300));
            order.setPaymentDate(LocalDateTime.now().plusDays(300));
            orderDao.update(order);
        });
        System.out.println(orderDao.findById(2L));
    }

    private static void updateLiquidLineTest() {
        var liquidLineDao = LiquidLineDao.getInstance();
        var mayBeLiquidLIne = liquidLineDao.findById(2L);
        System.out.println(mayBeLiquidLIne);

        mayBeLiquidLIne.ifPresent(liquidLine -> {
            liquidLine.setLiquidLine("Boshki Boshki");
            liquidLineDao.update(liquidLine);
        });
        System.out.println(liquidLineDao.findById(2L));
    }

    public static void updateBaseTest() {
        var baseDao = BaseDao.getInstance();
        var mayBeBase = baseDao.findById(1L);
        System.out.println(mayBeBase);

        mayBeBase.ifPresent(liquidLine -> {
            liquidLine.setPrVgRatio("50/50");
            baseDao.update(liquidLine);
        });
        System.out.println(baseDao.findById(2L));
    }

    private static void updateLiquidTasteTest() {
        var liquidTasteDao = FlavourTypeDao.getInstance();
        var mayBeLiquidTaste = liquidTasteDao.findById(1L);
        System.out.println(mayBeLiquidTaste);
        mayBeLiquidTaste.ifPresent(flavourType -> {
            flavourType.setFlavourType("Shoria");
            liquidTasteDao.update(flavourType);
        });
        System.out.println(liquidTasteDao.findById(1L));
    }

    private static void updateManufacturerTest() {
        var manufacturerDao = ManufacturerDao.getInstance();
        var mayBeManufacturer = manufacturerDao.findById(1L);
        System.out.println(mayBeManufacturer);
        mayBeManufacturer.ifPresent(manufacturer -> {
            manufacturer.setManufacturer("HASKUIIII");
            manufacturerDao.update(manufacturer);
        });
        System.out.println(manufacturerDao.findById(1L));
    }

    private static void updateNicConcentrationTest() {
        var nicConcentrationDao = NicConcDao.getInstance();
        var mayBeNicConcentration = nicConcentrationDao.findById(8L);
        System.out.println(mayBeNicConcentration);
        mayBeNicConcentration.ifPresent(concentration -> {
            concentration.setNicConcentration("20 SUPER STRONG");
            nicConcentrationDao.update(concentration);
        });
        System.out.println(nicConcentrationDao.findById(8L));
    }

    private static void updateNicTypeTest() {
        var niceType = NicTypeDao.getInstance();
        var mayBeNicType = niceType.findById(5L);
        System.out.println(mayBeNicType);
        mayBeNicType.ifPresent(nicType -> {
            nicType.setNicType("SUPER DUPER NEW");
            niceType.update(nicType);
        });
        System.out.println(niceType.findById(5L));
    }

    private static void updateLiquidTest(Long id) {
        var liquidDao = LiquidDao.getInstance();
        var mayBeLiquid = liquidDao.findById(id);
        System.out.println(mayBeLiquid);
        mayBeLiquid.ifPresent(liquid -> {
            liquid.setPrice(BigDecimal.valueOf(1000));
            liquid.setNicType(NicTypeDao.getInstance().findById(2L).orElse(null));
            liquidDao.update(liquid);
        });
        System.out.println(liquidDao.findById(id));
    }

    private static void saveUserTest() {
        var savedUser = UserDao.getInstance().save(User.builder()
                .firstName("Petr")
                .lastName("Petr")
                .dateOfBirth(LocalDateTime.of(1991, 2, 2, 0, 0))
                .address("Petr")
                .email("Petr@af.com")
                .mobilePhone(String.valueOf(895165455547L))
                .password("123123")
                .build());
        System.out.println(savedUser);
    }

    private static void saveOrderTest() {
        var savedOrder = OrderDao.getInstance().save(Order.builder()
                .user(UserDao.getInstance().findById(2L).orElse(null))
                .orderDate(LocalDateTime.of(2022, 12, 12, 0, 0))
                .isPaid(false)
                .paymentDate(LocalDateTime.of(0, 1, 1, 0, 0))
                .build());
        System.out.println(savedOrder);
    }

    private static void saveLiquidLineTest() {
        var savedLiquidLine = LiquidLineDao.getInstance().save(
                LiquidLine.builder()
                        .liquidLine("Sweet Sour")
                        .build());
        System.out.println(savedLiquidLine);
    }

    private static void saveLiquidTasteTest() {
        var savedLiquidTaste = FlavourTypeDao.getInstance().save(FlavourType.builder()
                .flavourType("Forest home").
                build());
        System.out.println(savedLiquidTaste);
    }

    private static void saveBaseTest() {
        var savedBase = BaseDao.getInstance().save(
                Base.builder()
                        .prVgRatio("65/35")
                        .build());
        System.out.println(savedBase);
    }

    private static void saveManufacturerTest() {
        var savedManufacturer = ManufacturerDao.getInstance().save(
                Manufacturer.builder()
                        .manufacturer("BOSHKIIIOO")
                        .build());
        System.out.println(savedManufacturer);
    }

    private static void saveNicConcentrationTest() {
        var savedNicConcentration = NicConcDao.getInstance().save(
                NicConc.builder()
                        .nicConcentration("20 SUPER DUPER STRONG")
                        .build()
        );
        System.out.println(savedNicConcentration);
    }

    private static void saveNicTypeTest() {
        var savedNicType = NicTypeDao.getInstance().save(
                NicType.builder()
                        .nicType("NEW TYPE OF NIC!")
                        .build());
        System.out.println(savedNicType);
    }

    private static void saveLiquidTest() {
        var savedLiquid = LiquidDao.getInstance().save(
                Liquid.builder()
                        .manufacturer(ManufacturerDao.getInstance().findById(1L).orElse(null))
                        .liquidLine(LiquidLineDao.getInstance().findById(2L).orElse(null))
                        .flavourType(FlavourTypeDao.getInstance().findById(1L).orElse(null))
                        .description("This is the best liquid!")
                        .nicType(NicTypeDao.getInstance().findById(1L).orElse(null))
                        .nicConc(NicConcDao.getInstance().findById(2L).orElse(null))
                        .base(BaseDao.getInstance().findById(1L).orElse(null))
                        .originCountry(OriginCountryDao.getInstance().findById(1L).orElse(null))
                        .price(BigDecimal.valueOf(666))
                        .stock(333L)
                        .build());
        System.out.println(savedLiquid);
    }

    private static void deleteUserTest(Long id) {
        var userDao = UserDao.getInstance();
        var deletedUser = userDao.delete(id);
        System.out.println(deletedUser);
    }

    private static void deleteLiquidTest(Long id) {
        var liquidDao = LiquidDao.getInstance();
        var deletedLiquid = liquidDao.delete(1L);
        System.out.println(deletedLiquid);
    }

    private static void deleteBaseTest() {
        var baseDao = BaseDao.getInstance();
        var deletedBase = baseDao.delete(6L);
        System.out.println(deletedBase);
    }

    private static void deleteLiquidTasteTest() {
        var liquidTasteDao = FlavourTypeDao.getInstance();
        var deletedTaste = liquidTasteDao.delete(11L);
        System.out.println(deletedTaste);
    }

    private static void deleteNicConcentrationTest() {
        var nicConcentrationDao = NicConcDao.getInstance();
        var deletedNicConcentration = nicConcentrationDao.delete(8L);
        System.out.println(deletedNicConcentration);
    }

    private static void findAllOrdersTest() {
        var orders = OrderDao.getInstance().findAll();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void findAllLiquidLinesTest() {
        var liquidLines = LiquidLineDao.getInstance().findAll();
        for (LiquidLine liquidLine : liquidLines) {
            System.out.println(liquidLine);
        }
    }


    private static void findAllBaseTest() {
        var baseLines = BaseDao.getInstance().findAll();
        for (Base baseLine : baseLines) {
            System.out.println(baseLine);
        }
    }

    private static void findAllLiquidTastesTest() {
        var liquidTastes = FlavourTypeDao.getInstance().findAll();
        for (FlavourType flavourType : liquidTastes) {
            System.out.println(flavourType);
        }
    }

    private static void findAllManufacturerTest() {
        var manufacturers = ManufacturerDao.getInstance().findAll();
        for (Manufacturer manufacturer : manufacturers) {
            System.out.println(manufacturer);
        }
    }

    private static void findAllNicConcentrationTest() {
        var nicConcentrationDao = NicConcDao.getInstance().findAll();
        for (NicConc nicConc : nicConcentrationDao) {
            System.out.println(nicConc);
        }
    }

    private static void findAllNicTypeTest() {
        var nicTypes = NicTypeDao.getInstance().findAll();
        for (NicType nicType : nicTypes) {
            System.out.println(nicType);
        }
    }

    private static void findAllLiquidsTest() {
        var liquids = LiquidDao.getInstance().findAll();
        for (Liquid liquid : liquids) {
            System.out.println(liquid);
        }
    }

    private static void deleteOrderTest(Long id) {
        var orderDao = OrderDao.getInstance();
        var deleteResult = orderDao.delete(6L);
        System.out.println(deleteResult);
    }

    private static void deleteLiquidLineTest(Long id) {
        var liquidLineDao = LiquidLineDao.getInstance();
        var deleteResult = liquidLineDao.delete(12L);
        System.out.println(deleteResult);
    }

    private static void deleteManufacturerTest() {
        var manufacturerDao = ManufacturerDao.getInstance();
        var deletedManufacturer = manufacturerDao.delete(16L);
        System.out.println(deletedManufacturer);
    }

    private static void deleteNicTypeTest() {
        var nicTypeDao = NicTypeDao.getInstance();
        var deletedNicType = nicTypeDao.delete(7L);
        System.out.println(deletedNicType);
    }
}


import dao.LiquidBaseDao;
import dao.LiquidDao;
import dao.LiquidLineDao;
import dao.LiquidTasteDao;
import dao.ManufacturerDao;
import dao.NicConcDao;
import dao.NicTypeDao;
import dao.OrderDao;
import dao.OriginCountryDao;
import dao.RoleDao;
import dao.UserDao;
import entity.Liquid;
import entity.LiquidBase;
import entity.LiquidLine;
import entity.LiquidTaste;
import entity.Manufacturer;
import entity.NicConc;
import entity.NicType;
import entity.Order;
import entity.Role;
import entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DaoRunner {
    public static void main(String[] args) {
//        saveUserTest();
//        deleteUserTest(4L);
//        findAllUsersTest();
//        findByIdUserTest(3L);
//        updateUserTest(3L);
//        findByIdUserTest(3L);

//        saveOrderTest();
//        deleteOrderTest(4L);
//        findAllOrdersTest();
//        finByIdOrderTest(3L);
//        updateOrderTest(3L);
//        finByIdOrderTest(3L);


//        saveLiquidTest();
//        deleteLiquidTest(2L);
//        findAllLiquidsTest();
//        findByIdLiquidTest(1L);
//        updateLiquidTest(1L);
//        findByIdLiquidTest(1L);
//
//
//        saveLiquidLineTest("Some New Line");
//        deleteLiquidLineTest(13L);
//        findAllLiquidLinesTest();
//        findByIdLiquidLineTest(1L);
//        updateLiquidLineTest(1L, "Something New");
//        findByIdLiquidLineTest(1L);
//
//
//        saveBaseTest("85/15");
//        deleteBaseTest(7L);
//        findAllBaseTest();
//        findByIdBaseTest(1L);
//        updateBaseTest();
//        findByIdBaseTest(1L);
//
//        saveLiquidTasteTest("Forest Zen");
//        deleteLiquidTasteTest(10L);
//        findAllLiquidTastesTest();
//        findByIdLiquidTasteTest(1L);
//        updateLiquidTasteTest(1L, "Shoria");
//        findByIdLiquidTasteTest(1L);
//
//        saveManufacturerTest("DUAL");
//        deleteManufacturerTest(15L);
//        findAllManufacturerTest();
//        findByIdManufacturerTest(14L);
//        updateManufacturerTest(14L, "Leeearmonth");
//        findByIdManufacturerTest(14L);
//
//        saveNicConcentrationTest("20 SUPER STRONG");
//        deleteNicConcentrationTest(9L);
//        findAllNicConcentrationTest();
//        findByIdNicConcentrationTest(8L);
//        updateNicConcentrationTest(8L, "20 SUPER HARD");
//        findByIdNicConcentrationTest(8L);
//
//        saveNicTypeTest("SOME NEW TYPE1");
//        deleteNicTypeTest(5L);
//        findAllNicTypeTest();
//        findByIdNicTypeTest(4L);
//        updateNicTypeTest(4L, "SOME SUPER NEW TYPE");
//        findByIdNicTypeTest(4L);

//        saveRoleTest("SUPER ADMIN");
//        deleteRoleTest(3L);
//        findAllRolesTest();
        saveRoleTest("SUPER ADMIN");
        findByIdRoleTest(4L);
        updateRoleTest(4L, "SOME ROLE");
        findByIdRoleTest(4L);
    }


    private static void findByIdUserTest(Long id) {
        System.out.println(UserDao.getInstance().findById(id));
    }

    private static void finByIdOrderTest(Long id) {
        var mayBeOrder = OrderDao.getInstance().findById(id);
        System.out.println(mayBeOrder);
    }


    private static void findByIdLiquidLineTest(Long id) {
        var mayBeLiquidLine = LiquidLineDao.getInstance().findById(id);
        System.out.println(mayBeLiquidLine);
    }


    private static void findByIdLiquidTasteTest(Long id) {
        var liquidTasteDao = LiquidTasteDao.getInstance();
        var mayBeLiquidTaste = liquidTasteDao.findById(id);
        System.out.println(mayBeLiquidTaste);
    }

    private static void findByIdBaseTest(Long id) {
        var baseDao = LiquidBaseDao.getInstance();
        var mayBeBase = baseDao.findById(id);
        System.out.println(mayBeBase);
    }

    private static void findByIdManufacturerTest(Long id) {
        var mayBeManufacturer = ManufacturerDao.getInstance().findById(id);
        System.out.println(mayBeManufacturer);
    }

    private static void findByIdNicConcentrationTest(Long id) {
        var nicConcentrationDao = NicConcDao.getInstance();
        var mayNicConcentrationDao = nicConcentrationDao.findById(id);
        System.out.println(mayNicConcentrationDao);
    }

    private static void findByIdNicTypeTest(Long id) {
        var mayBeNicType = NicTypeDao.getInstance().findById(id);
        System.out.println(mayBeNicType);
    }

    private static void findByIdLiquidTest(Long id) {
        var instance = LiquidDao.getInstance();
        var liquid = instance.findById(id);
        System.out.println(liquid);
    }

    private static void findByIdRoleTest(Long roleId) {
        var roleDao = RoleDao.getInstance();
        var liquid = roleDao.findById(roleId);
        System.out.println(liquid);
    }

    private static void updateUserTest(Long id) {
        var userDao = UserDao.getInstance();
        var mayBeUser = userDao.findById(id);
        System.out.println(mayBeUser);

        mayBeUser.ifPresent(user -> {
            user.setFirstName("Petr");
            user.setLastName("Petrov");
            userDao.update(user);
        });
    }

    private static void updateOrderTest(Long id) {
        var orderDao = OrderDao.getInstance();
        var mayBeOrder = orderDao.findById(id);
        System.out.println(mayBeOrder);

        mayBeOrder.ifPresent(order -> {
            order.setPaid(true);
            order.setOrderDate(LocalDateTime.now().minusDays(300));
            order.setPaymentDate(LocalDateTime.now().plusDays(300));
            orderDao.update(order);
        });
        System.out.println(orderDao.findById(2L));
    }

    private static void updateLiquidLineTest(Long id, String liquidLineName) {
        var liquidLineDao = LiquidLineDao.getInstance();
        var mayBeLiquidLIne = liquidLineDao.findById(id);
        System.out.println(mayBeLiquidLIne);

        mayBeLiquidLIne.ifPresent(liquidLine -> {
            liquidLine.setLiquidLine(liquidLineName);
            liquidLineDao.update(liquidLine);
        });
        System.out.println(liquidLineDao.findById(2L));
    }

    public static void updateBaseTest() {
        var baseDao = LiquidBaseDao.getInstance();
        var mayBeBase = baseDao.findById(1L);
        System.out.println(mayBeBase);

        mayBeBase.ifPresent(liquidLine -> {
            liquidLine.setBase("50/50");
            baseDao.update(liquidLine);
        });
        System.out.println(baseDao.findById(2L));
    }

    private static void updateLiquidTasteTest(Long id, String newName) {
        var liquidTasteDao = LiquidTasteDao.getInstance();
        var mayBeLiquidTaste = liquidTasteDao.findById(id);
        System.out.println(mayBeLiquidTaste);
        mayBeLiquidTaste.ifPresent(liquidTaste -> {
            liquidTaste.setLiquidTaste(newName);
            liquidTasteDao.update(liquidTaste);
        });
        System.out.println(liquidTasteDao.findById(1L));
    }

    private static void updateManufacturerTest(Long id, String newManufName) {
        var manufacturerDao = ManufacturerDao.getInstance();
        var mayBeManufacturer = manufacturerDao.findById(id);
        System.out.println(mayBeManufacturer);
        mayBeManufacturer.ifPresent(manufacturer -> {
            manufacturer.setManufacturer(newManufName);
            manufacturerDao.update(manufacturer);
        });
        System.out.println(manufacturerDao.findById(1L));
    }

    private static void updateNicConcentrationTest(Long id, String newNicConcName) {
        var nicConcentrationDao = NicConcDao.getInstance();
        var mayBeNicConcentration = nicConcentrationDao.findById(id);
        System.out.println(mayBeNicConcentration);
        mayBeNicConcentration.ifPresent(concentration -> {
            concentration.setNicConcentration(newNicConcName);
            nicConcentrationDao.update(concentration);
        });
        System.out.println(nicConcentrationDao.findById(8L));
    }

    private static void updateNicTypeTest(Long id, String renamedNicType) {
        var niceType = NicTypeDao.getInstance();
        var mayBeNicType = niceType.findById(id);
        System.out.println(mayBeNicType);
        mayBeNicType.ifPresent(nicType -> {
            nicType.setNicType(renamedNicType);
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

    private static void updateRoleTest(Long roleId, String roleNewName) {
        var roleDao = RoleDao.getInstance();
        var mayBeRole = roleDao.findById(roleId);
        System.out.println(mayBeRole);
        mayBeRole.ifPresent(role -> {
            role.setRole(roleNewName);
            roleDao.update(role);
        });
        System.out.println(roleDao.findById(roleId));
    }

    private static void saveUserTest() {
        var savedUser = UserDao.getInstance().save(User.builder().firstName("Petr").lastName("Petr").dateOfBirth(LocalDate.from(LocalDateTime.of(1991, 2, 2, 0, 0))).address("Petr").email("Petr@af.com").mobilePhone(String.valueOf(895165455547L)).password("123123").role("2").build());
        System.out.println(savedUser);
    }

    private static void saveOrderTest() {
        var savedOrder = OrderDao.getInstance().save(Order.builder().user(UserDao.getInstance().findById(2L).orElse(null)).orderDate(LocalDateTime.of(2022, 12, 12, 0, 0)).isPaid(false).paymentDate(LocalDateTime.of(0, 1, 1, 0, 0)).build());
        System.out.println(savedOrder);
    }

    private static void saveLiquidLineTest(String liquidLineName) {
        var savedLiquidLine = LiquidLineDao.getInstance().save(LiquidLine.builder().liquidLine(liquidLineName).build());
        System.out.println(savedLiquidLine);
    }

    private static void saveLiquidTasteTest(String newLiquidLine) {
        var savedLiquidTaste = LiquidTasteDao.getInstance().save(LiquidTaste.builder().liquidTaste(newLiquidLine).build());
        System.out.println(savedLiquidTaste);
    }

    private static void saveBaseTest(String newBaseName) {
        var savedBase = LiquidBaseDao.getInstance().save(LiquidBase.builder().base(newBaseName).build());
        System.out.println(savedBase);
    }

    private static void saveManufacturerTest(String newManufacturerName) {
        var savedManufacturer = ManufacturerDao.getInstance().save(Manufacturer.builder().manufacturer(newManufacturerName).build());
        System.out.println(savedManufacturer);
    }

    private static void saveNicConcentrationTest(String newConc) {
        var savedNicConcentration = NicConcDao.getInstance().save(NicConc.builder().nicConcentration(newConc).build());
        System.out.println(savedNicConcentration);
    }

    private static void saveNicTypeTest(String newNicType) {
        var savedNicType = NicTypeDao.getInstance().save(NicType.builder().nicType(newNicType).build());
        System.out.println(savedNicType);
    }

    private static void saveLiquidTest() {
        var savedLiquid = LiquidDao.getInstance().save(
                Liquid.builder()
                        .manufacturer(ManufacturerDao.getInstance().findById(1L).orElse(null))
                        .liquidLine(LiquidLineDao.getInstance().findById(2L).orElse(null))
                        .liquidTaste(LiquidTasteDao.getInstance().findById(1L).orElse(null))
                        .description("This is the best liquid!")
                        .nicType(NicTypeDao.getInstance().findById(1L).orElse(null))
                        .nicConc(NicConcDao.getInstance().findById(2L).orElse(null))
                        .liquidBase(LiquidBaseDao.getInstance().findById(1L).orElse(null))
                        .originCountry(OriginCountryDao.getInstance().findById(1L).orElse(null))
                        .price(BigDecimal.valueOf(666)).stock(333L).build());
        System.out.println(savedLiquid);
    }

    private static void saveRoleTest(String newRole) {
        var savedRole = RoleDao.getInstance().save(Role.builder()
                .role(newRole)
                .build());
        System.out.println(savedRole);
    }

    private static void deleteUserTest(Long id) {
        var userDao = UserDao.getInstance();
        var deletedUser = userDao.delete(id);
        System.out.println(deletedUser);
    }

    private static void deleteLiquidTest(Long id) {
        var deletedLiquid = LiquidDao.getInstance().delete(id);
        System.out.println(deletedLiquid);
    }

    private static void deleteBaseTest(Long id) {
        var deletedBase = LiquidBaseDao.getInstance().delete(id);
        System.out.println(deletedBase);
    }

    private static void deleteLiquidTasteTest(Long id) {
        var deletedTaste = LiquidTasteDao.getInstance().delete(id);
        System.out.println(deletedTaste);
    }

    private static void deleteNicConcentrationTest(Long id) {
        var deletedNicConcentration = NicConcDao.getInstance().delete(id);
        System.out.println(deletedNicConcentration);
    }

    private static void deleteRoleTest(Long roleId) {
        var deletedRole = RoleDao.getInstance().delete(roleId);
        System.out.println(deletedRole);
    }

    private static void findAllUsersTest() {
        var users = UserDao.getInstance().findAll();
        for (User user : users) {
            System.out.println(user);
        }
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
        var baseLines = LiquidBaseDao.getInstance().findAll();
        for (LiquidBase liquidBaseLine : baseLines) {
            System.out.println(liquidBaseLine);
        }
    }

    private static void findAllLiquidTastesTest() {
        var liquidTastes = LiquidTasteDao.getInstance().findAll();
        for (LiquidTaste liquidTaste : liquidTastes) {
            System.out.println(liquidTaste);
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

    private static void findAllRolesTest() {
        var roles = RoleDao.getInstance().findAll();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    private static void deleteOrderTest(Long id) {
        var orderDao = OrderDao.getInstance();
        var deleteResult = orderDao.delete(id);
        System.out.println(deleteResult);
    }

    private static void deleteLiquidLineTest(Long id) {
        var liquidLineDao = LiquidLineDao.getInstance();
        var deleteResult = liquidLineDao.delete(id);
        System.out.println(deleteResult);
    }

    private static void deleteManufacturerTest(Long id) {
        var deletedManufacturer = ManufacturerDao.getInstance().delete(id);
        System.out.println(deletedManufacturer);
    }

    private static void deleteNicTypeTest(Long id) {
        var deletedNicType = NicTypeDao.getInstance().delete(id);
        System.out.println(deletedNicType);
    }
}


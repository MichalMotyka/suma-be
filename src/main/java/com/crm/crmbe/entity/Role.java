package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Role {
    public final long id;
    private final String name;
    private boolean active;

    public static final List<Role> roleList = new ArrayList<>(Arrays.asList(
            new Role(1,"adresList",false),
            new Role(2,"adresView",false),
            new Role(3,"adresEdit",false),
            new Role(4,"adresCreate",false),
            new Role(5,"adresDeleteState",false),
            new Role(6,"adresGetByGus",false),
            new Role(7,"adresGetAllNoState",false),
            new Role(8,"adresGetAllNoHistoric",false),
            new Role(9,"adresGetById",false),
            new Role(10,"componentGetAll",false),
            new Role(11,"componentDelete",false),
            new Role(12,"componentGet",false),
            new Role(13,"componentSave",false),
            new Role(14,"componentDelete",false),
            new Role(15,"contractCreate",false),
            new Role(16,"contractGetByPP",false),
            new Role(17,"contractGetById",false),
            new Role(18,"contractGetByUid",false),
            new Role(19,"contractDelete",false),
            new Role(20,"contractEdit",false),
            new Role(21,"contractGetAll",false),
            new Role(22,"contractActivation",false),
            new Role(23,"contractGetByContractor",false),
            new Role(24,"countryList",false),
            new Role(25,"countryCreate",false),
            new Role(26,"countryDelete",false),
            new Role(27,"contractorSave",false),
            new Role(28,"contractorList",false),
            new Role(29,"contractorGet",false),
            new Role(30,"contractorDelete",false),
            new Role(31,"contractorGetUid",false),
            new Role(32,"meterCreate",false),
            new Role(33,"meterGet",false),
            new Role(34,"meterGetByID",false),
            new Role(35,"meterGetUid",false),
            new Role(36,"meterRemove",false),
            new Role(37,"otGetAll",false),
            new Role(38,"otDelete",false),
            new Role(39,"otCreate",false),
            new Role(40,"otEdit",false),
            new Role(41,"otVeryfy",false),
            new Role(42,"ppCreate",false),
            new Role(43,"ppGet",false),
            new Role(44,"ppGetByUid",false),
            new Role(45,"ppGetByID",false),
            new Role(46,"priceGetAll",false),
            new Role(47,"priceSave",false),
            new Role(48,"priceDelete",false),
            new Role(49,"priceGetByUid",false),
            new Role(50,"priceGetById",false),
            new Role(51,"readingGetAll",false),
            new Role(52,"readingCreate",false),
            new Role(53,"readingActivate",false),
            new Role(54,"readingDelete",false),
            new Role(55,"readingClose",false),
            new Role(56,"readingGetById",false),
            new Role(57,"readingsElementCreate",false),
            new Role(58,"tarifSave",false),
            new Role(59,"tarifDelete",false),
            new Role(60,"tarifGetById",false),
            new Role(61,"tarfiGetByUid",false),
            new Role(62,"usersGetAll",false),
            new Role(63,"usersGetRoles",false),
            new Role(64,"usersRegister",false)));
}

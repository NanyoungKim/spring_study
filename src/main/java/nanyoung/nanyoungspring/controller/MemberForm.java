package nanyoung.nanyoungspring.controller;

public class MemberForm {
    private String name;    //createMemberForm.html의 name="name"과 매칭됨

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

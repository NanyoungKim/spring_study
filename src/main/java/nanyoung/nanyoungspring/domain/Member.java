package nanyoung.nanyoungspring.domain;

public class Member {

    private Long id;    //고객 아이디 x. 시스템 저장시 구분용(임의의 값)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

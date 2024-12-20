package adeo.leroymerlin.cdp.DTOs;

import adeo.leroymerlin.cdp.entities.Member;

import java.util.Set;

public class BandDTO {
    private String name;
    private Set<Member> members;

    public BandDTO(String name, Set<Member> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}

package dev.floffah.skillpaths.skills;

public class SkillType {
    public static final SkillType AxeThrowing = new SkillType(250);

    public int XPWorth;

    public SkillType(int worth) {
        XPWorth = worth;
    }
}

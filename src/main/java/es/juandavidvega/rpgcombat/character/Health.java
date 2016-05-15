package es.juandavidvega.rpgcombat.character;

public class Health {

    public static final Integer MAX_HEALTH_POINTS = 1000;
    public static final Integer MIN_HEALTH_POINTS = 0;
    
    private Integer points;

    public Health(Integer points) {
        this.points = points;
    }

    public void subtract(Integer damage) {
        points -= damage;
        sanitizeHealth();
    }

    public void add(Integer points) {
        if(isDead()) return;
        this.points += points;
        sanitizeHealth();
    }

    public boolean isAlive() {
        return points > 0;
    }

    private void sanitizeHealth() {
        checkMaxHealth();
        checkMinHealth();
    }

    private void checkMinHealth() {
        if (points < 0) points = 0;
    }

    private void checkMaxHealth() {
        if (points > 1000) points = 1000;
    }

    public boolean isDead() {
        return !isAlive();
    }

    public Integer points (){
        return points;
    }

}

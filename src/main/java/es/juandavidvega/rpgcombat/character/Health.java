package es.juandavidvega.rpgcombat.character;

public class Health {

    public static final Double MAX_HEALTH_POINTS = 1000.0;
    public static final Double MIN_HEALTH_POINTS = .0;
    
    private Double points;

    public Health(Double points) {
        this.points = points;
    }

    public void subtract(Double damage) {
        points -= damage;
        sanitizeHealth();
    }

    public void add(Double points) {
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
        if (points < 0) points = 0d;
    }

    private void checkMaxHealth() {
        if (points > 1000) points = 1000d;
    }

    public boolean isDead() {
        return !isAlive();
    }

    public Double points (){
        return points;
    }

}

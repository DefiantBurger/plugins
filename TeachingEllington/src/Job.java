public class Job {

    private String name;
    private double hourlyWage;

    public Job(String name, double hourlyWage) {
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public void employ(String name, double hourlyWage) {
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public String toString() {
        return String.format("Job{" + "name='%s', hourlyWage=%.02f}", name, hourlyWage);
    }

    public String getName() {
        return name;
    }
    public double getHourlyWage() {
        return hourlyWage;
    }

}

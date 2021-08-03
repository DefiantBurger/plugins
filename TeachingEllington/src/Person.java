public class Person {

    private String name;
    private int age;
    private Job job;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        job = new Job("Unemployed", 0);
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Job getJob() {
        return job;
    }

}

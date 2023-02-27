public class BMICalculator {
    private double weight;
    private double height;

    public BMICalculator(double weight, double height) { // class constructor
        this.weight = weight; // set weight to the passed parameter
        this.height = height; // set height to the passed parameter
    }

    public double calculateBMI() { // method that calculates the BMI
        return weight / (height * height); // return calculated BMI
    }

    public String getWeightCat(double bmi) { // meth to get weight category based on user's calculated BMI
        if (bmi < 18.5) { // if BMI is less than 18.5
            return "underweight"; // return 'underweight'
        } else if (bmi < 25) { // if BMI is between 18.5 and 24.9
            return "normal weight"; // return 'normal weight'
        } else if (bmi < 30) { // if BMI is between 25 and 29.9
            return "overweight"; // return 'overweight'
        } else { // if BMI is 30 or higher
            return "obese"; // return 'obese'
        }
    }
}
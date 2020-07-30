public class Main {
    
    public static abstract class DoctorsVisitProcess {
        public String prepareForExamination() {
            return "1). Check in at the registration desk, then get a medical examination. \n";
        }
        public abstract String mainTreatment();
        public abstract String finalTreatment();
    }

    public static class HeartTreatment extends DoctorsVisitProcess {

        @Override
        public String mainTreatment() {
            return "2). 5 medicine injections at the hospital every two days.\n";
        }
    
        @Override
        public String finalTreatment() {
            return "3). Patient needs a course of prescribed medicine for two months.";
        }
    }

    public static class BrokenLegTreatment extends DoctorsVisitProcess {
        @Override
        public String mainTreatment() {
            return "2). A full cast on the broken leg is required. \n";
        }
    
        @Override
        public String finalTreatment() {
            return "3). After the removal of cast, patient needs to get more physical activity.";
        }
    }

    public static class BackProblemsTreatment extends DoctorsVisitProcess {
        @Override
        public String mainTreatment() {
            return "2). A spinal surgery is required. \n";
        }
    
        @Override
        public String finalTreatment() {
            return "3). After surgery, patient needs a massage therapy.";
        }
    }

    public static class Treatment {
        public String getTreatment(DoctorsVisitProcess step) {
            String fullTreatment = "";
            fullTreatment += step.prepareForExamination() +
                    step.mainTreatment() +
                    step.finalTreatment();
            return fullTreatment;
        }
    }

    public static void main(String[] args) {
        var treatment = new Treatment();
        var heart = treatment.getTreatment(new HeartTreatment());
        System.out.println(heart);
        System.out.println("***");
        var leg = treatment.getTreatment(new BrokenLegTreatment());
        System.out.println(leg);
        System.out.println("***");
        var back = treatment.getTreatment(new BackProblemsTreatment());
        System.out.println(back);
        System.out.println("***");
    }
}
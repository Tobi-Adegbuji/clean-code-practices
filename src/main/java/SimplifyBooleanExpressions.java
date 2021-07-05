import lombok.Data;

public class SimplifyBooleanExpressions {


    static class SpaceShip {

        Crew crew;
        FuelTank fuelTank;
        Hull hull;
        Navigator navigator;
        OxygenTank oxygenTank;


        //Boolean expressions that combine multiple conditions are
        //hard to understand and get wrong. Such as this example below.
        boolean wilLCrewSurvive_badExample() {
            return hull.holes == 0 &&
                    fuelTank.fuel >= navigator.requiredFuelToEarth() &&
                    oxygenTank.lastsFor(crew.size) > navigator.timeToEarth();
        }


        //SOLUTION
        //A clean way to avoid this is to replace the complex grouping with high level functions.
        //Here we created high level methods to replace the complex boolean logic.
        //This code is so much more readable!
        boolean willCrewSurvive() {
            boolean hasEnoughResources = hasEnoughFuel() && hasEnoughOxygen();
            return hull.isIntact() && hasEnoughResources;
        }


        private boolean hasEnoughOxygen() {
            return oxygenTank.lastsFor(crew.size) > navigator.timeToEarth();
        }


        private boolean hasEnoughFuel() {
            return fuelTank.fuel >= navigator.requiredFuelToEarth();
        }



    }

}


class Crew {
    final int size = 3;
}

@Data
class FuelTank {
    final double fuel = 9;
}

class Hull {

    int holes = 0;

    //Always avoid negated boolean value names. Its easier to think about boolean logic that way.
    public boolean isIntact() {
        return true;
    }
}


@Data
class Navigator {
    public double requiredFuelToEarth() {
        return 7.5;
    }

    public double timeToEarth() {
        return 4;
    }

}

@Data
class OxygenTank {
    public double lastsFor(int crewSize) {
        return 8;
    }
}
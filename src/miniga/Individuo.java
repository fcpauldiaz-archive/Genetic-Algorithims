/**
* Universidad Del Valle de Guatemala
* 22-nov-2015
* Pablo Díaz 13203
*/

package miniga;

/**
 *
 * @author Pablo
 */
public class Individuo implements Comparable<Individuo>{
    
    private int cromosomaX;
    private int cromosomaY;
    private double fitness;
    private double acumFitness;

    public Individuo(int cromosomaX, int cromosomaY) {
        this.cromosomaX = cromosomaX;
        this.cromosomaY = cromosomaY;
    }

    public Individuo() {
    }

    public int getCromosomaX() {
        return cromosomaX;
    }

    public void setCromosomaX(int cromosomaX) {
        this.cromosomaX = cromosomaX;
    }

    public int getCromosomaY() {
        return cromosomaY;
    }

    public void setCromosomaY(int cromosomaY) {
        this.cromosomaY = cromosomaY;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getAcumFitness() {
        return acumFitness;
    }

    public void setAcumFitness(double acumFitness) {
        this.acumFitness = acumFitness;
    }

    @Override
    public String toString() {
        return "Individuo{" + "cromosomaX=" + cromosomaX + ", cromosomaY=" + cromosomaY + ", fitness=" + fitness + '}';
    }

    @Override
    public int compareTo(Individuo o) {
        if (this.fitness>o.getAcumFitness())
            return 1;
        else if (this.fitness<o.fitness)
            return -1;
        return 0;
    }
    
    

}

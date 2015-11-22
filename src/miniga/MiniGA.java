/**
* Universidad del Valle de Guatemala
* Pablo Diaz 13203
*/

package miniga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Pablo
 */
public class MiniGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Individuo> poblacion = new ArrayList();
        Random random = new Random();
        //realizar una poblacion inicial de 50 individuos
        //con cromosomas aleatorios
        for (int i = 0;i<100;i++){
            Integer[] result = genRandom();
            poblacion.add(new Individuo(result[0],result[1]));
        }
        int iteraciones = 4;
        while (true){
             double acumFit = 0;
             //Se calcula la fitness Total
             for (int j = 0;j<poblacion.size();j++){
                 acumFit += f1(poblacion.get(j).getCromosomaX(),poblacion.get(j).getCromosomaY());
             }
              double acum = 0;
             //Paso 2. Se vuelve a calcular la fitness individual y se normaliza
             for (int j = 0;j<poblacion.size();j++){
                 double fit = f1(poblacion.get(j).getCromosomaX(),poblacion.get(j).getCromosomaY());
                 acum += fit;
                 double fit2 = fit/acumFit;
                 
                 poblacion.get(j).setFitness(fit2);

             }
             
              for (int j = 0;j<poblacion.size();j++){
                poblacion.get(j).setAcumFitness(acumHasta(poblacion,j));
            }
              Collections.sort(poblacion,Collections.reverseOrder());
              
              System.out.println("");
              //Paso 3. Seleccionar los 2 padres individuos más fit
              ArrayList<Individuo> seleccionados = new ArrayList();
              for (int j = 0;j<poblacion.size()/2;j++){
                double rand = random.nextDouble();
                for (int i = 0;i<poblacion.size();i++){
                   
                    if (poblacion.get(i).getAcumFitness()>rand){
                        seleccionados.add(poblacion.get(i));
                        break;
                    }
                }
              }
              
              
              ArrayList<Individuo> nuevaPoblacion = new ArrayList();
              for (int j = 0;j+2<=seleccionados.size();j=j+2){
                  double pc = random.nextDouble();
                  Individuo nuevo = new Individuo();
                  int cont = 2;
                  if (pc>=0.7){
                    nuevo.setCromosomaX(seleccionados.get(j).getCromosomaX());
                    nuevo.setCromosomaX(seleccionados.get(++j).getCromosomaY());
                  }
                  else{
                 nuevo.setCromosomaX(seleccionados.get(j).getCromosomaX());
                 nuevo.setCromosomaX(seleccionados.get(++j).getCromosomaY()); 
                  }
                 nuevaPoblacion.add(nuevo);
                 
              }
              System.out.println(nuevaPoblacion.size());
              int r = random.nextInt(nuevaPoblacion.size()+1);
              for (int j = 0;j<nuevaPoblacion.size();j++){
                  if (r == j){
                      Integer[] result = genRandom();
                      nuevaPoblacion.get(j).setCromosomaX(result[0]);
                      nuevaPoblacion.get(j).setCromosomaY(result[1]);
                  }
                  
              }
             
            
              poblacion = nuevaPoblacion;
              if (iteraciones == 0)
                  break;
              
              iteraciones--;
                System.out.println(poblacion);
             
        }
      
    }
    
    
    public static double f1(int x1,int x2){
        
        return 15*x1+30*x2+4*x1*x2-Math.pow(2*x1,2)-Math.pow(4*x2,2);
    }
    public static double acumHasta(ArrayList<Individuo> num, int hasta){
        double returnNum = 0;
        for (int i = 0;i<=hasta;i++){
            returnNum += num.get(i).getFitness();
        }
        return returnNum;
    }
    
    public static Integer[] genRandom(){
        Integer[] result = new Integer[2];
        Random random = new Random();
        int x1 = random.nextInt(31);
        int x2 = random.nextInt(31);
        while (x1+2*x2>=30){
            x1 = random.nextInt(31);
            x2 = random.nextInt(31);
        }
        //System.out.println(x1+x2*2);
        result[0] = x1;
        result[1] = x2;
        return result;
    }
}

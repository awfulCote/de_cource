package org.example
import scala.collection.mutable
import scala.io.StdIn.readLine
import scala.util.control.Breaks

/**
 * @author ${user.name}
 */
object App {

  def main(args : Array[String]) {
    //выводит фразу «Hello, Scala!» справа налево
    val string =  "Hello, Scala!"
    println(string.reverse)

    //переводит всю фразу в нижний регистр
    println(string.toLowerCase())

    //удаляет символ!
    println(string.replace("!", ""))

    //добавляет в конец фразы «and goodbye python!»
    println(string + " and goodbye python!")


/*    Напишите программу, которая вычисляет ежемесячный оклад сотрудника после вычета налогов.
    На вход вашей программе подается значение годового дохода до вычета налогов, размер премии – в процентах от годового дохода и компенсация питания.*/

    var yearSalary = 0
    var bonus = 0.0
    var foodCompensation = 0
    var monthlySalary = 0.0
    def getInputs(): Unit ={
      println("type year salary: ")
      yearSalary = readLine().toInt
      println("type year bonus: ")
      bonus = readLine().toFloat
      println("type food Compensation: ")
      foodCompensation = readLine().toInt
      monthlySalary = ((yearSalary + yearSalary*bonus/100)/12 + foodCompensation)*0.87
    }
    getInputs()
    println("monthly salary is " + monthlySalary)


    /*Напишите программу, которая рассчитывает для каждого сотрудника отклонение(в процентах) от среднего значения оклада на уровень всего отдела.
    В итоговом значении должно учитываться в большую или меньшую сторону отклоняется размер оклада.
    На вход вышей программе подаются все значения, аналогичные предыдущей программе,
    а также список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75.*/

    var depSalaries = mutable.MutableList(100, 150, 200, 80, 120, 75)

    val avgSalary = depSalaries.sum / depSalaries.size
    val deviationSalPer = monthlySalary/avgSalary*100 - 100
    println("Worker deviation " + deviationSalPer.round + "%")

    /*Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел) необходимую сумму с учетом результатов прошлого задания.
    Добавьте его зарплату в список и вычислите значение самой высокой зарплаты и самой низкой.*/

    println("how to change employee's salary?")
    val salaryDelta = readLine().toInt
    val newSalary = monthlySalary + salaryDelta
    depSalaries += newSalary.toInt
    val maxSalary = depSalaries.max
    val minSalary = depSalaries.min

    println(f"max salary is: $maxSalary;\nmin salary is: $minSalary.")


    /*Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей.
    Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.*/
    depSalaries += 350
    depSalaries += 90
    depSalaries = depSalaries.sorted
    println("Sorted team: " + depSalaries)

/*    Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч.
    Вычислите самостоятельно номер сотрудника в списке так, чтобы сортировка не нарушилась и добавьте его на это место.*/
    val newEmpSalary = 130
    var index = 1
    val closestSal = depSalaries.minBy(v => math.abs(v - newEmpSalary))

    val loop = Breaks
    loop.breakable {
      for (n <- depSalaries) {
        if (n == closestSal) {
          loop.break()
        }
        else
          index = index + 1
      }
    }
    def addNewWorkerSalary(list: mutable.MutableList[Int], i: Int, value: Int) ={
      list.take(i) ++ List(value) ++ list.drop(i)
    }
    depSalaries = addNewWorkerSalary(depSalaries, index, newEmpSalary)

    println("New department salaries: " + depSalaries)

/*    Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
      На входе программе подается «вилка» зарплаты специалистов уровня middle.*/

    println("type middle min salary: ")
    val minMiddleSalary = readLine().toInt

    println("type middle max salary: ")
    val maxMiddleSalary = readLine().toInt

    def detectSalaryLevel(list: mutable.MutableList[Int], minS: Int, maxS: Int):mutable.MutableList[Int]= {
      val newList = mutable.MutableList[Int]()
      for (n <- list){
        if ((n >= minS)&&(n < maxS)) {
          newList += n
        }
      }
      newList
    }
    val middleSalaries = detectSalaryLevel(depSalaries, minMiddleSalary,maxMiddleSalary )
    println("Middle workers salary: " + middleSalaries)

/*    Однако наступил кризис и ваши сотрудники требуют повысить зарплату.
      Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7%*/

    val inflation = 1.07
    def addInflation(list: mutable.MutableList[Int], inflation: Double):mutable.MutableList[Int]= {
      val newList = mutable.MutableList[Int]()
      for (n <- list){
        newList += (n*inflation).toInt
      }
      newList
    }
    depSalaries = addInflation(depSalaries, inflation)
    println("Add inflation: " + depSalaries)

  }

}

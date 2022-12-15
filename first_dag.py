from datetime import datetime

from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python_operator import PythonOperator
import random
from airflow.sensors.python import PythonSensor
from airflow.operators.python import BranchPythonOperator


pg_hostname = 'host.docker.internal'
pg_port = '5430'
pg_username = 'postgres'
pg_pass = 'password'
pg_db = 'test'

# a
def rand_int():
    print(random.random())
    print(random.random())

# c
def append_num():
    file1 = open("myfile.txt", "a") 
    num1 = random.random()
    num2 = random.random()
    file1.write(str(num1) + " " + str(num2) +"\n")
    file1.close()

# e
def write_num():
    with open('myfile.txt', 'r') as file:
        try:
            data = file.readlines()
            count = len(data)
            num1 = random.random()
            num2 = random.random()
            data[count-1] = str(num1) + " " + str(num2) + "\n"
        except IndexError:
            data = str(num1) + " " + str(num2) + "\n"

    with open('myfile.txt', 'w') as file:
        file.writelines(data)

# d
def calc_sum():
    file =  open('myfile.txt', 'r')
    data = file.readlines()
    sum_1 = 0.0
    sum_2 = 0.0
    for x in data:
        sum_1 += float(x.split(" ")[0])
        sum_2 += float(x.split(" ")[1])
    diff = sum_1 - sum_2
    file.close()

    with open('myfile.txt', 'a') as file:
        file.write(str(diff))

    

# A DAG represents a workflow, a collection of tasks
with DAG(dag_id="first_dag", start_date=datetime(2022, 12, 15), schedule="1,2,3,4,5 * * * *") as dag:
    

    # Tasks are represented as operators
    #python_task_a = PythonOperator(task_id="rand", python_callable = rand_int)
    #python_task_c = PythonOperator(task_id="append_num", python_callable = append_num)
    python_task_d = PythonOperator(task_id="calc_sum", python_callable = calc_sum)
    python_task_e = PythonOperator(task_id="write_num", python_callable = write_num)


    # Set dependencies between tasks
    python_task_e >> python_task_d 

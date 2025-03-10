import { api_url } from "@/lib/utils"
import axios from "axios"
export const getWorkoutData = async() => {
  return [
    {
      "name": "Workout1",
      "": ""
    },
  ]

  const response = await axios.get(`${api_url}/api/plan/workouts`, {
    withCredentials: true
  })
  .then(resp => {
    console.log("Workouts: " + JSON.stringify(resp.data))
  })
  .catch(e =>
    console.log("Error occured while fetching workouts: " + e)
  )

  // try {
  //   const response = await axios.get(`${api_url}/api/user`)
  //   return response.data
  // } catch (error) {
  //   console.error('Error fetching workout data:', error)
  //   throw error
  // }
}

export type Routine = {
  name: string
} 

export type Workout = {
  name: string
}

export async function getListOfRoutines(): Promise<Routine[]> {
  const response = await axios.get(`${api_url}/api/plan/routines`, {
      withCredentials: true
    })
    .then(resp => {
      console.log("Routines: " + JSON.stringify(resp.data))
      return resp.data.map((routine: any) => ({
        name: routine.name
      }))
    })
    .catch(e => {
      console.log("Error occured while fetching routines: " + e)
      return []
    })
  return response
}

export async function getListOfWorkouts(): Promise<Workout[]> {
  const response = await axios.get(`${api_url}/api/plan/workouts`, {
    withCredentials: true
  })
  .then(resp => {
    console.log("Workouts: " + JSON.stringify(resp.data))
    return resp.data.map((routine: any) => ({
      name: routine.name
    }))
  })
  .catch(e => {
    console.log("Error occured while fetching workouts: " + e)
    return []
  })
  return response
}

export async function createWorkout(name: string) {
  const response = await axios.post(`${api_url}/api/plan/workouts`,
    [
      {Name: name}
    ],
    {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      console.log("Workout created successfully");
      return true
    }).catch(e => {
      console.log("Error occured while creating workouts: " + e)
      return false
    })
  return response
}

export async function createRoutine(name: string) {
  const response = await axios.post(`${api_url}/api/plan/routines`,
    [
      {Name: name}
    ],
    {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      console.log("Routine created successfully");
      return true
    }).catch(e => {
      console.log("Error occured while creating routine: " + e)
      return false
    })
  return response
}

// export const getListOfWorkouts = async (): Promise<Workout[]> => {
//   // Mock data for testing
//   await new Promise(resolve => setTimeout(resolve, 5000));
//   let data = [
//     { id: 1, name: "Workout 1" },
//     { id: 2, name: "Workout 2" },
//     { id: 3, name: "Workout 3" }
//   ];

//   return data.map((routine: any) => ({
//     name: routine.name
//   }))
// }
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Button } from "../../ui/button"
import { ColumnDef } from "@tanstack/react-table"
import { getListOfRoutines, getListOfWorkouts, Workout } from "@/services/Api"
import { useEffect, useState } from "react"
import { DataTable } from "./WorkoutsDataTable"
import ViewIndividualWorkout from "./ViewIndividualWorkout"
import CreateNewWorkout from "./CreateNewWorkout"
export default function ViewWorkouts() {
  const [workoutList, setWorkoutList] = useState<Workout[]>([])

  const fetchWorkouts = () => {

    getListOfWorkouts().then((list) => {
      setWorkoutList(list);
      console.log(list);
    })
  }

  return (
    <Dialog>
      <DialogTrigger onClick={fetchWorkouts}>
        <Button className="bg-red-700 font-bold text-lg text-white hover:bg-red-400 py-8 w-full">
          View Workouts
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Workouts</DialogTitle>
        </DialogHeader>
        <DataTable columns={columns} data={workoutList}/>
        <CreateNewWorkout />
      </DialogContent>
    </Dialog>
  )
}

export const columns: ColumnDef<Workout>[] = [
  {
    accessorKey: "name",
    header: "Name",
  },
  {
    id: "view",
    cell: ({row}) => {
      return (
        <div className="flex justify-end gap-4">
          <ViewIndividualWorkout />
          <Dialog>
            <DialogTrigger>
              <Button variant="destructive">
                Delete
              </Button>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader>
                <DialogTitle>Delete this workout</DialogTitle>
                <DialogDescription>Are you sure you want to delete this workout? This action is irreversible.</DialogDescription>
              </DialogHeader>
              <Button variant="destructive">
                Proceed to deletion
              </Button>
            </DialogContent>
          </Dialog>
        </div>
      )
    }
  }
  // {
  //   accessorKey: "email",
  //   header: "Email",
  // },
  // {
  //   accessorKey: "amount",
  //   header: "Amount",
  // },
]
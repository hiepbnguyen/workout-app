import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Button } from "../../ui/button"
import { ColumnDef } from "@tanstack/react-table"
import { getListOfRoutines, Routine } from "@/services/Api"
import { useEffect, useState } from "react"
import { DataTable } from "./RoutinesDataTable"
import ViewIndividualRoutine from "./ViewIndividualRoutine"
import CreateNewRoutines from "./CreateNewWorkout"
export default function ViewRoutines() {
  const [routineList, setRoutineList] = useState<Routine[]>([])

  const fetchRoutines = () => {

    getListOfRoutines().then((list) => {
      setRoutineList(list);
      console.log(list);
    })
  }

  return (
    <Dialog>
      <DialogTrigger onClick={fetchRoutines}>
        <Button className="bg-blue-700 font-bold text-lg text-white hover:bg-blue-400 py-8 w-full">
          View Routines
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Routines</DialogTitle>
        </DialogHeader>
        <DataTable columns={columns} data={routineList}/>
        <CreateNewRoutines />
      </DialogContent>
    </Dialog>
  )
}

export const columns: ColumnDef<Routine>[] = [
  {
    accessorKey: "name",
    header: "Name",
  },
  {
    id: "view",
    cell: ({row}) => {
      return (
        <div className="flex justify-end gap-4">
          <ViewIndividualRoutine />
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
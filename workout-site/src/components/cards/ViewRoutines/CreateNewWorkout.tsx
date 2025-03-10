import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Button } from "../../ui/button"
import { useState } from "react"
import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { createRoutine } from "@/services/Api"

export default function CreateNewRoutines() {
  const [name, setName] = useState("")

  const ConfirmCreateRoutine = async() => {
    if (name != "") {
      await createRoutine(name)
    }
  }

  return (
    <Dialog>
      <DialogTrigger>
        <Button>
          Create New Routines
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Routines</DialogTitle>
        </DialogHeader>
        <Label htmlFor="name">Name</Label>
        <Input placeholder="Routine name" value={name} onChange={e => {setName(e.target.value)}}></Input>
        <Button onClick={ConfirmCreateRoutine}>Create</Button>
      </DialogContent>
    </Dialog>
    )
}
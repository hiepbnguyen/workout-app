import { Button } from "@/components/ui/button"
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"

export default function ViewIndividualWorkout() {
  return (
    <Dialog>
      <DialogTrigger>
        <Button variant="outline">
          Edit
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>This Workout</DialogTitle>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  )
}
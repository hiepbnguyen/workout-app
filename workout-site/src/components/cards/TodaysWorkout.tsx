import { Button } from "../ui/button";
import { Card } from "../ui/card";

export default function TodaysWorkout() {
  const items = [
    {
      name: 'Leg Press'
    },
    {
      name: 'Leg Extensions'
    },
    {
      name: 'Leg Curls'
    },
    {
      name: 'Leg'
    }
  ]
  return (
    <Card className="p-10 gap-5 flex items-center col-span-full">
      <div className="grid w-full grid-cols-1 lg:grid-cols-8">
        <div className="col-span-3 flex flex-col justify-center">
          <div className="text-sm text-gray-300">
            Today's Routine
          </div>
          <div className="text-5xl font-bold">
            Leg Day
          </div>
        </div>


        <div className="col-span-3 py-6">
          <div className="flex flex-col gap-2">
            {items.map((item, index) => (
              <div className="font-mono h-auto" key={item.name}>
                {/* {index + 1 + '. ' + item.name} */}
                {'• ' + item.name}
              </div>
            ))}
          </div>
        </div>


        <div className="grid grid-rows-2 gap-5 col-span-2">
          <Button className="bg-green-700 font-bold text-lg text-white hover:bg-green-400 py-8">
            Start
          </Button>
          <Button className="bg-gray-700 font-bold text-lg text-white hover:bg-gray-400 py-8">
            Edit Routine
          </Button>
        </div>
      </div>
    </Card>
  )
}
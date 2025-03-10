import { Card } from "../ui/card";
import { Flame } from "lucide-react";

export default function FitnessStreak() {
  const streak = 5;
  return(
    <Card className="p-5 gap-5 flex items-center">
      <Flame size={58}/>
      <div>
        <div className="text-sm text-gray-300">
          Fitness Streak
        </div>
        <div className="text-4xl font-bold pr-10">
          {streak} Days
        </div>
      </div>
    </Card>
  )
}
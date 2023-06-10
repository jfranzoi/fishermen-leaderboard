'use client';

import { useFishermen } from './hooks/useFishermen';
import FishermanCard from './components/FishermanCard';

export default function Home() {

  const [fishermen, isLoading] = useFishermen();

  if (isLoading) return <p>Loading...</p>
  if (!fishermen) return <p>No results</p>

  return (
    <main>
      <div>
        <h1>Leaderboard</h1>
        {fishermen.map((each) => {
          return (
            <FishermanCard content={each} />
          );
        })}
      </div>
    </main>
  )
}

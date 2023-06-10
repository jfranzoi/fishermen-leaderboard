'use client';

import { useFishermen } from './hooks/useFishermen';
import FishermanCard from './components/FishermanCard';
import styles from './page.module.css';

export default function Home() {

  const [fishermen, isLoading, loadNext] = useFishermen();

  if (isLoading) return <p>Loading...</p>

  return (
    <main>
      <div className={styles.container}>
        <h1 className={styles.header}>Top Scorer</h1>
        {fishermen.map((each) => {
          return (
            <FishermanCard content={each} />
          );
        })}
        <div><a href='#' onClick={loadNext}>Load More</a></div>
      </div>
    </main>
  )
}

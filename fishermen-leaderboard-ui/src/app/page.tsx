'use client'
import { useFishermen } from '@/hooks/useFishermen'
import FishermanCard from '@/components/FishermanCard/FishermanCard';

import Link from 'next/link'
import styles from './page.module.css'

export default function Home() {
  const [fishermen, isLoading, loadNext] = useFishermen();

  return (
    <main className={styles.main}>
      <div className={styles.description}>
        <h1>Top Fishermen</h1>
      </div>

      <div className={styles.grid}>
        {fishermen.map((each, index) => {
          return (
            <Link className={styles.card} href={`/detail/${each.id}`}>
              <FishermanCard key={each.id} item={each} rank={index + 1} />
            </Link>
          );
        })}
      </div>

      {fishermen.length > 0 && (
        <button onClick={loadNext} disabled={isLoading}>
          {!isLoading ? 'Load More' : '...'}
        </button>
      )}
    </main>
  )
}

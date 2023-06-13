'use client'

import { useFishermanDetail } from '@/hooks/useFishermen';
import RecollectionDetails from '@/components/RecollectionDetails/RecollectionDetails';
import Image from 'next/image';

import styles from './page.module.css'

export default function Details({ params }: { params: { id: string } }) {
  const id = params.id
  const [fisherman, isLoading] = useFishermanDetail(id)

  return fisherman && (
    <main className={styles.main}>
      <div className={styles.description}>
        <h1>{fisherman.name}</h1>
      </div>
      <Image alt={fisherman.name} src={fisherman.picture} width={200} height={200} />

      <div className={styles.grid}>
        {fisherman.recollections.map((each) => (
          <div className={styles.card}>
            <RecollectionDetails item={each} />
          </div>
        ))}
      </div>
    </main>
  )
}
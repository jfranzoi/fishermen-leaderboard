import './globals.css'

export const metadata = {
  title: 'Leaderboard'
}

declare type Props = {
  children: React.ReactNode
}

export default function RootLayout({ children }: Props) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  )
}

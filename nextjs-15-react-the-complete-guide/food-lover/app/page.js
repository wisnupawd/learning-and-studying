import Link from 'next/link'

// @ meaning the root folder that is set in jsconfig.json
import Header from '@/components/header'

export default function Home() {
  return (
    <main>
      <Header />
      <p>🔥 Let&apos;s get started! 🔥</p>
      <p><Link href="/about">About Us</Link></p>
    </main>
  );
}

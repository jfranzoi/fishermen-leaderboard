/** @type {import('next').NextConfig} */
const nextConfig = {
    output: 'standalone',
    images: {
        remotePatterns: [
            {
                protocol: 'https',
                hostname: 'ogyre.fra1.digitaloceanspaces.com',
                port: '',
                pathname: '**',
            },
        ],
    },
}

module.exports = nextConfig

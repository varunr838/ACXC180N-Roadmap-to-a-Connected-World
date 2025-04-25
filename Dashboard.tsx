"use client";

import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Calendar } from "@/components/ui/calendar";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useState } from "react";

export default function Dashboard() {
  const [date, setDate] = useState<Date | undefined>(new Date());

  return (
    <div className="min-h-screen bg-muted/40 p-6 flex flex-col gap-6">
      {/* Top Section */}
      <div className="space-y-2">
        <h1 className="text-4xl font-bold">
          Beautifully designed components <br /> built using Radix UI and Tailwind CSS
        </h1>
        <p className="text-muted-foreground max-w-2xl">
          Accessible and customizable components that you can copy and paste into your apps. Free. Open Source.
        </p>
      </div>

      {/* Cards Section */}
      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        {/* Create project Card */}
        <Card className="col-span-1">
          <CardHeader>
            <CardTitle>Create project</CardTitle>
            <p className="text-sm text-muted-foreground">Deploy your new project in one-click.</p>
          </CardHeader>
          <CardContent className="space-y-4">
            <Input placeholder="Name of your project" />
            <Select>
              <SelectTrigger>
                <SelectValue placeholder="Select framework" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="next">Next.js</SelectItem>
                <SelectItem value="sveltekit">SvelteKit</SelectItem>
                <SelectItem value="nuxt">Nuxt.js</SelectItem>
                <SelectItem value="remix">Remix</SelectItem>
                <SelectItem value="astro">Astro</SelectItem>
              </SelectContent>
            </Select>
            <div className="flex justify-end gap-2">
              <Button variant="ghost">Cancel</Button>
              <Button>Deploy</Button>
            </div>
          </CardContent>
        </Card>

        {/* Buttons Example */}
        <div className="flex flex-col items-start justify-center gap-4">
          <div className="flex gap-2">
            <Button>Button</Button>
            <Button variant="outline">Outline</Button>
          </div>
        </div>

        {/* Revenue + Tabs + Calendar Card */}
        <Card className="col-span-1 lg:col-span-2">
          <CardHeader className="flex flex-row justify-between items-center">
            <div>
              <p className="text-sm text-muted-foreground">Total Revenue</p>
              <h2 className="text-2xl font-bold">$45,231.89</h2>
              <p className="text-xs text-green-600">+20.1% from last month</p>
            </div>
            <Tabs defaultValue="overview" className="w-[300px]">
              <TabsList>
                <TabsTrigger value="overview">Overview</TabsTrigger>
                <TabsTrigger value="analytics">Analytics</TabsTrigger>
                <TabsTrigger value="reports">Reports</TabsTrigger>
                <TabsTrigger value="notifications">Notifications</TabsTrigger>
              </TabsList>
            </Tabs>
          </CardHeader>
          <CardContent>
            <Calendar mode="single" selected={date} onSelect={setDate} className="rounded-md border" />
          </CardContent>
        </Card>
      </div>
    </div>
  );
}

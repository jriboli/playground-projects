import { defineConfig, devices } from "@playwright/test";

export default defineConfig({
  testMatch: ["tests/login.test.ts"],
});

package com.example.trainapp.ui.util

/**
 * Enumeration of different types of navigation used in the Train Application.
 * This enum defines the main navigation styles or layouts that can be used within the app.
 *
 * - BOTTOM_NAVIGATION: Represents a navigation style where items are located at the bottom of the screen, typically used in mobile layouts.
 * - NAVIGATION_RAIL: Represents a navigation style with a vertical list of items on the side of the content area, suitable for tablet or desktop layouts.
 * - PERMANENT_NAVIGATION_DRAWER: Represents a permanently visible drawer for navigation, commonly used in larger screen layouts like desktops.
 */
enum class TrainNavigationType {
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL,
    PERMANENT_NAVIGATION_DRAWER,
}

# Toolbar_Frag_Transitions

## Question

How viable (in terms of UI) is it to have different toolbar UI for different fragments?

## Hypothesis

Functionally possible, but it will not look good.
Simpler UI transitions may look passable, but more complex UI will be unusable in production without spending massive amounts of time creating new functionality or tweaking existing ones.

## Test

**Fragment #1:**

- Translucent toolbar
- Static toolbar
- Top of content meets the top of the toolbar

**Fragment #2:**

- Opaque toolbar
- Toolbar scrolls in sync with the content
- Top of content meets the bottom of the toobar

Alignment and scrolling behavior is set by a CollapsingToolbarLayout's scroll flags and a FragmentContainerView's scroll behavior.

Tapping a button replaces one fragment with the other, back and forth.

## Result

Each fragment by itself looks fine, but transitioning between the two shows problems.

Tapping the button will immediately move the top of the content to the same guidelines as the top of the content for the other fragment. The toolbar also does not transition.

Unknown if the toolbar can be animated, since it is set by layout parameters that don't seem to be tied to anything that I can animate myself.

If the fragment's content's top location can be kept as it while transitioning to a fragment with a different type of alignment, this seems like something that would be good for a production application.
I personally wouldn't want to use this is this problem exists.
